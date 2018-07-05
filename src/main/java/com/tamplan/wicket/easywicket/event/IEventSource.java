package com.tamplan.wicket.easywicket.event;

public interface IEventSource {

	public void addEventLink(Class<? extends IEvent<?>> eventType, Object target);
	
	public void addEventLink(Class<? extends IEvent<?>> eventType, Object target, String method);
		
	public void removeEventLink(Class<? extends IEvent<?>> eventType, Object target);
	
	public void dispatchEvent(IEvent<?> event);
}
