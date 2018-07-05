package com.tamplan.wicket.easywicket.web.common;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.event.EventSource;
import com.tamplan.wicket.easywicket.event.IEvent;

public abstract class EasyPanel extends Panel implements IEasyWicketContainer {

	private static final long serialVersionUID = 1L;
	
	private EventSource eventSource;

	public EasyPanel(String id) {
		super(id);
		
		initPanel();
	}

	public EasyPanel(String id, IModel<?> model) {
		super(id, model);
		
		initPanel();
	}
	
	private void initPanel() {
		eventSource = new EventSource();
	}
	
	public void addEventLink(Class<? extends IEvent<?>> eventType, Serializable target) {
		eventSource.addEventLink(eventType, target);
	}
	
	public void addEventLink(Class<? extends IEvent<?>> eventType, Serializable target, String method) {
		eventSource.addEventLink(eventType, target, method);
	}
	
	public void dispatchEvent(IEvent<?> event) {
		eventSource.dispatchEvent(event);
	}
	
	public void removeEventLink(Class<? extends IEvent<?>> eventType, Serializable target) {
		eventSource.removeEventLink(eventType, target);
	}
	
	public void initValues() {
	}
	
	public void pack() {
	}
	
	public void setCurrentWidgetContext(WidgetContext widgetContext) {
	}
}
