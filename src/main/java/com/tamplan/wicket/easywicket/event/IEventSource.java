package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;

public interface IEventSource {

	public void addEventLink(Class<? extends IEvent<?>> eventType, Serializable target);
	
	public void addEventLink(Class<? extends IEvent<?>> eventType, Serializable target, String method);
		
	public void removeEventLink(Class<? extends IEvent<?>> eventType, Serializable target);
	
	public void dispatchEvent(IEvent<?> event);
}
