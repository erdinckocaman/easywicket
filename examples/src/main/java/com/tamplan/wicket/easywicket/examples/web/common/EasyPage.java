package com.tamplan.wicket.easywicket.examples.web.common;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.event.EventHandler;
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

	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target) {
		eventSource.addEventLink(eventType, target);
	}

	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target, String method) {
		eventSource.addEventLink(eventType, target, method);
	}

	@Override
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, EventHandler<T> eventHandler) {
		eventSource.addEventLink(eventType, eventHandler);
	}

	public <T extends IEvent<?>> void dispatchEvent(T event) {
		eventSource.dispatchEvent(event);
	}

	public <T extends IEvent<?>> void removeEventLink(Class<T> eventType, Serializable target) {
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
