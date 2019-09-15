package com.tamplan.wicket.easywicket;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import java.io.Serializable;
import java.util.List;

public abstract class BaseWrapper implements IEasyWicketWrapper, Serializable {

	private static final long serialVersionUID = 1L;
	
	protected EasyWicketUtil util;

	public BaseWrapper() {
		util = EasyWicketUtil.getInstance();
	}

	public final Component create(
			String widgetId, 
			Class<? extends Component> widgetClass, 
			EasyWicket annot,
			MarkupContainer parentWidget) {
		
		Component component = createInstance(widgetId, widgetClass, annot, parentWidget);

		util.initComponentWithAnnot(component, annot);

		return component;
	}

	protected abstract Component createInstance(
			String widgetId, 
			Class<? extends Component> widgetClass,
			EasyWicket annot, 
			MarkupContainer parentWidget);

	protected List<Object> getList(EasyWicket annot, MarkupContainer parentWidget) {
		return util.getContainerValue(parentWidget, annot.list());

	}



}
