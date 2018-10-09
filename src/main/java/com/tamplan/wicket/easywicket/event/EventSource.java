package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EventSource implements IEventSource, Serializable{
		
	private static final long serialVersionUID = 1L;
	private Map<Class<? extends IEvent<?>> , EventLinkRecord> eventLinks;
	
	// check if the target object has the proper event handler function 
	// when calling addEventLink function
	// private boolean checkWhenLinking;
	
	// eventsource can take a parameterized type T which is source of the event
	
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target) {
		addEventLink(eventType, target, "processEvent");
	}

	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target, String method) {
		Objects.requireNonNull(eventType);
		Objects.requireNonNull(target);
		Objects.requireNonNull(method);

		initEventLinks(eventType);
		
		EventLinkRecord<T> rec = eventLinks.get(eventType);
		rec.addLink(target, method);
		
	}

	@Override
	public <T extends IEvent<?>> void addEventLink(Class<T> eventType, EventHandler<T> eventHandler) {
		initEventLinks(eventType);
		
		EventLinkRecord<T> rec = eventLinks.get(eventType);
		rec.addLink(eventHandler);
	}

	
	private <T extends IEvent<?>> void initEventLinks(Class<T> eventType) {
		if (eventLinks == null) {
			eventLinks = new HashMap<>();
		}
		
		if ( !eventLinks.containsKey(eventType)) {
			eventLinks.put(eventType, new EventLinkRecord<T>(eventType));
		}
	}
	

	public <T extends IEvent<?>> void dispatchEvent(T event) {
		Objects.requireNonNull(event);
		
		if ( eventLinks == null ) {
			return;
		}
		
		if ( eventLinks.containsKey(event.getClass()) ) {
			eventLinks.get(event.getClass()).dispatchEvent(event);
		}
	}

	public <T extends IEvent<?>> void removeEventLink(Class<T> eventType, Serializable target) {
		Objects.requireNonNull(eventType);
		Objects.requireNonNull(target);
		
		if ( eventLinks == null ) {
			return;
		}
		
		if ( eventLinks.containsKey(eventType) ) {
			eventLinks.get(eventType).removeLink(target);
		}
	}
}
