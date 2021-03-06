package com.tamplan.wicket.easywicket;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.application.IComponentInitializationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EasyWicketComponentInitializer implements IComponentInitializationListener {

	private static Logger logger = LoggerFactory.getLogger(EasyWicketComponentInitializer.class);

	private EasyWicketFactory factory;

	private static class FieldInfo {
		Field field;
		EasyWicket annot;
		Component widget;
		String widgetId;
		String parentId;
	}

	public EasyWicketComponentInitializer() {
		factory = EasyWicketFactory.getInstance();
	}

	public void onInitialize(Component component) {
		if (!(component instanceof MarkupContainer)) {
			return;
		}

		if (!(component instanceof IEasyWicketContainer)) {
			return;
		}

		IEasyWicketContainer container = (IEasyWicketContainer) component;

		container.initContainer();

		LinkedList<Field> fields = new LinkedList<>();
		
		getAllFields(component.getClass(), fields);
		
		if (fields.isEmpty() ) {
			return;
		}

		Map<String, FieldInfo> fieldInfoMap = new HashMap<>();

		for (Field field : fields) {
			if (!field.isAnnotationPresent(EasyWicket.class)) {
				continue;
			}
			EasyWicket annot = field.getAnnotation(EasyWicket.class);
			
			FieldInfo fi = getFieldInfo(field, annot);
			
			//might have overlapping ids if there are inner easy wicket container objects
			fieldInfoMap.put(fi.annot.id(), fi);
		}

		MarkupContainer rootComponent = (MarkupContainer) component;

		instantiateWidgets(fieldInfoMap, rootComponent);

		container.packContainer();
	}
	
	private void getAllFields(Class<?> clazz, List<Field> fieldsList) {
		Field[] fields = clazz.getDeclaredFields();
		
 		if ( fields != null && fields.length > 0 ) {
 			for(Field f: fields) {
 				fieldsList.add(f);
 			}
 		}
 		
 		if (clazz.getSuperclass() != null) {
 			getAllFields(clazz.getSuperclass(), fieldsList);
 		}
	}

	private FieldInfo getFieldInfo(Field field, EasyWicket annot) {
		Class<?> type = field.getType();
		
		if (logger.isDebugEnabled()) {
			logger.debug("field name={}, type={}", field.getName(), type);
		}

		FieldInfo fi = new FieldInfo();
		
		fi.annot = annot;
		fi.field = field;
		fi.widgetId = extractWidgetId(annot.id());
		fi.parentId = extractParentId(annot.id(), fi.widgetId);

		return fi;

	}

	private void instantiateWidgets(Map<String, FieldInfo> fieldInfoMap, MarkupContainer rootComponent) {
		for (Map.Entry<String, FieldInfo> entry : fieldInfoMap.entrySet()) {
			instantiateWidget(entry.getValue(), fieldInfoMap, rootComponent);
		}
	}

	private void instantiateWidget(FieldInfo fi, Map<String, FieldInfo> fieldInfoMap, MarkupContainer rootContainer) {
		String parentId = fi.parentId;

		if (parentId == null || parentId.length() == 0) {
			if (fi.widget == null) {
				addToParent(fi, rootContainer, rootContainer);
			}
		} else {
			FieldInfo parentFi = fieldInfoMap.get(parentId);
			if (parentFi == null) {
				throw new IllegalStateException("no parent found for annot id=" + fi.annot.id() + " parent id=" + parentId);
			}

			if (parentFi.widget == null) {
				instantiateWidget(parentFi, fieldInfoMap, rootContainer);
			}

			if (fi.widget == null) {
				addToParent(fi, (MarkupContainer) parentFi.widget, rootContainer);
			}
		}

	}

	private void addToParent(FieldInfo fi, MarkupContainer parentWidget, MarkupContainer rootContainer) {
		Component widget = factory.createWidget(fi.widgetId, (Class<? extends Component>) fi.field.getType(), fi.annot, parentWidget);
		
		parentWidget.add(widget);
		
		fi.widget = widget;

		try {
			fi.field.setAccessible(true);
			fi.field.set(rootContainer, widget);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String extractWidgetId(String annotationId) {
		int index = annotationId.lastIndexOf('.');
		
		if (index < 0) {
			return annotationId;
		}

		if (index + 1 < annotationId.length()) {
			return annotationId.substring(index + 1, annotationId.length());
		} else {
			throw new IllegalArgumentException("no widget id extracted from annotation id=" + annotationId);
		}

	}

	private String extractParentId(String annotationId, String widgetId) {
		if (widgetId.equals(annotationId)) {
			return null;
		}
		return annotationId.substring(0, annotationId.length() - widgetId.length() - 1);
	}
}
