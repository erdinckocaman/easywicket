package com.tamplan.wicket.easywicket.web.common;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.event.EventSource;
import com.tamplan.wicket.easywicket.event.IEvent;
import com.tamplan.wicket.easywicket.event.IEventSource;

public abstract class EasyPage extends WebPage implements IEventSource, IEasyWicketContainer {

	private static final long serialVersionUID = 1L;
	private EventSource eventSource;
	private WidgetContext currentWidgetContext;

	public EasyPage() {
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

	public void pack() {
	}

	public void initValues() {
	}

	public void setCurrentWidgetContext(WidgetContext widgetContext) {
		currentWidgetContext = widgetContext;
	}
	
	public WidgetContext getCurrentWidgetContext() {
		return currentWidgetContext;
	}
}
