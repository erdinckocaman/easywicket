package com.tamplan.wicket.easywicket;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EasyWicketComponentListener implements
		IComponentInstantiationListener {

	private static Logger logger = LoggerFactory.getLogger(EasyWicketComponentListener.class);
	
	private EasyWicketFactory factory;
	
	private static class FieldInfo {
		Field field;
		EasyWicket annot;
		Component widget;
		String widgetId;
		String parentId;
	}
	
	public EasyWicketComponentListener() {
		factory = EasyWicketFactory.getInstance();
	}
	
	public void onInstantiation(Component component) {
		if ( ! ( component instanceof MarkupContainer )) {
			return;
		}
		
		if ( !(component instanceof IEasyWicketContainer) ) {
			return;
		}
		
		IEasyWicketContainer container = (IEasyWicketContainer) component;
		
		container.initValues();

		Field[] fields = component.getClass().getDeclaredFields();
		if ( fields == null || fields.length == 0 ) {
			return;
		}
		
		Map<String, FieldInfo> fieldInfoMap = new HashMap<String, FieldInfo>();
		
		for(Field field: fields) {
			if ( !field.isAnnotationPresent(EasyWicket.class)) {
				continue;
			}
			EasyWicket annot = field.getAnnotation(EasyWicket.class);
			FieldInfo fi = getFieldInfo(field, annot);
			fieldInfoMap.put(fi.annot.id(), fi);
		}
		
		MarkupContainer rootComponent = (MarkupContainer) component;
		
		instantiateWidgets(fieldInfoMap, rootComponent);
		
		
		container.pack();
	}

	private FieldInfo getFieldInfo(Field field, EasyWicket annot) {
		Class<?> type = field.getType();
		if ( logger.isInfoEnabled() ) {
			logger.info("field name=" + field.getName() + " type=" + type);
		}
		
		FieldInfo fi = new FieldInfo();
		fi.annot = annot;
		fi.field = field;
		fi.widgetId = extractWidgetId(annot.id());
		fi.parentId = extractParentId(annot.id(), fi.widgetId);
		
		return fi;
		
	}
	
	private void instantiateWidgets(Map<String, FieldInfo> fieldInfoMap, MarkupContainer rootComponent) {		
		for(Map.Entry<String, FieldInfo> entry: fieldInfoMap.entrySet()) {
			instantiateWidget(entry.getValue(), fieldInfoMap, rootComponent);
		}
	}
	
	private void instantiateWidget(FieldInfo fi,  Map<String, FieldInfo> fieldInfoMap, MarkupContainer rootContainer) {
		String parentId = fi.parentId;
		
		if ( parentId == null || parentId.length() == 0 ) {
			if ( fi.widget == null ) {
				addToParent(fi, rootContainer, rootContainer);
			}
		}else {
			FieldInfo parentFi = fieldInfoMap.get(parentId);
			if ( parentFi == null ) {
				throw new IllegalStateException("no parent found for annot id=" + fi.annot.id());
			}
			
			if ( parentFi.widget == null ) {
				instantiateWidget(parentFi, fieldInfoMap, rootContainer);
			}

			if ( fi.widget == null ) {
				addToParent(fi, (MarkupContainer)parentFi.widget, rootContainer);
			}
		}
		
	}
	
	private void addToParent(FieldInfo fi, MarkupContainer parentWidget, MarkupContainer rootContainer) {
		Component widget = factory.createWidget(fi.widgetId, 
				(Class<? extends Component>)fi.field.getType(), fi.annot, parentWidget);
		parentWidget.add(widget);
		fi.widget = widget;
		
		try {
			fi.field.setAccessible(true);
			fi.field.set(rootContainer, widget);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String extractWidgetId(String annotationId) {
		int index = annotationId.lastIndexOf(".");
		if ( index < 0 ) {
			return annotationId;
		}
		
		if ( index + 1 < annotationId.length() ) {
			return annotationId.substring(index + 1, annotationId.length());
		}else {
			throw new IllegalArgumentException("no widget id extracted from annotation id=" + annotationId);
		}
		
	}
	
	private String extractParentId(String annotationId, String widgetId) {
		if ( widgetId.equals(annotationId)) {
			return null;
		}
		
		String parentId = annotationId.substring(0, annotationId.length() - widgetId.length()-1);
		
		return parentId;
	}
	
	
	public static void main(String [] args) {
		EasyWicketComponentListener listener = new EasyWicketComponentListener();
		String annotationId = "form.name";
		String widgetId = listener.extractWidgetId(annotationId);
		String parentId = listener.extractParentId(annotationId, widgetId);
		System.out.println(widgetId);
		System.out.println(parentId);
		
		annotationId = "form";
		widgetId = listener.extractWidgetId(annotationId);
		parentId = listener.extractParentId(annotationId, widgetId);
		System.out.println(widgetId);
		System.out.println(parentId);
	}
	
}
