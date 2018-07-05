package com.tamplan.wicket.easywicket.web.common;

import java.util.Collection;

import org.apache.wicket.markup.html.WebPage;

import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.event.EventSource;
import com.tamplan.wicket.easywicket.event.IEvent;
import com.tamplan.wicket.easywicket.event.IEventSource;


public abstract class EasyPage extends WebPage implements IEventSource, IEasyWicketContainer {
	
	private static final long serialVersionUID = 1L;
	private EventSource eventSource;
	
	public EasyPage() {
		eventSource = new EventSource();
	}
	
	protected boolean isBlank(Collection<?> collection) {
		return collection != null && collection.size() == 0;
	}

	public void addEventLink(Class<? extends IEvent<?>> eventType, Object target) {
		eventSource.addEventLink(eventType, target);
	}
	
	public void addEventLink(Class<? extends IEvent<?>> eventType,
			Object target, String method) {
		eventSource.addEventLink(eventType, target, method);
	}
	
	public void dispatchEvent(IEvent<?> event) {
		eventSource.dispatchEvent(event);
	}
	
	public void removeEventLink(Class<? extends IEvent<?>> eventType,
			Object target) {
		eventSource.removeEventLink(eventType, target);
	}
	
	public void pack() {
	}
	
	public void initValues() {
	}
	
	public void setCurrentWidgetContext(WidgetContext widgetContext) {
	}
}
