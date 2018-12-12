package com.tamplan.wicket.easywicket.common;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.event.EventHandler;
import com.tamplan.wicket.easywicket.event.EventSource;
import com.tamplan.wicket.easywicket.event.IEvent;
import com.tamplan.wicket.easywicket.event.IEventSource;

public abstract class EasyPanel extends Panel implements IEasyWicketContainer, IEventSource {

	private static final long serialVersionUID = 1L;

	private EventSource eventSource;

	private WidgetContext currentWidgetContext;

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
	
	@Override
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, EventHandler<T> eventHandler) {
		eventSource.addEventLink(eventType, eventHandler);
	}
	
	@Override
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target) {
		eventSource.addEventLink(eventType, target);
	}
	
	@Override
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target, String method) {
		eventSource.addEventLink(eventType, target, method);
	}
	
	@Override
	public <T extends IEvent<?>> void removeEventLink(Class<T> eventType, Serializable target) {
		eventSource.removeEventLink(eventType, target);
		
	}
	
	@Override
	public <T extends IEvent<?>> void dispatchEvent(T event) {
		eventSource.dispatchEvent(event);
		
	}

	@Override
	public void initValues() {
	}

	@Override
	public void pack() {
	}
	
	@Override
	public void setCurrentWidgetContext(WidgetContext widgetContext) {
		currentWidgetContext = widgetContext;
	}

	public WidgetContext getCurrentWidgetContext() {
		return currentWidgetContext;
	}
}
