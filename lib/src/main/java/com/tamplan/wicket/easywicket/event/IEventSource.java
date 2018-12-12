package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;

public interface IEventSource {

	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target);
	
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target, String method);
	
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, EventHandler<T> eventHandler);
		
	public <T extends IEvent<?>> void removeEventLink(Class<T> eventType, Serializable target);
	
	public <T extends IEvent<?>> void dispatchEvent(T event);
}
