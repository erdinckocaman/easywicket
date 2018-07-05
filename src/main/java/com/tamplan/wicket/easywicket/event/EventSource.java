package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;
import java.util.ArrayList;

public class EventSource implements IEventSource, Serializable{
		
	private static final long serialVersionUID = 1L;
	private ArrayList<EventLinkRecord> eventLinks;
	
	// check if the target object has the proper event handler function 
	// when calling addEventLink function
	// private boolean checkWhenLinking;
	
	// eventsource can take a parameterized type T which is source of the event
	
	public EventSource() {
	}

	public void addEventLink(Class<? extends IEvent<?>> eventType, Object target) {
		addEventLink(eventType, target, "processEvent");
	}


	public void addEventLink(Class<? extends IEvent<?>> eventType, Object target, String method) {
		if (eventLinks == null)
			eventLinks = new ArrayList<EventLinkRecord>();
		
		EventLinkRecord rec = new EventLinkRecord(eventType);
		rec.addLink(target, method);
		eventLinks.add(rec);
	}

	public void dispatchEvent(IEvent<?> event) {
		if (eventLinks == null)
			return;
		
		for (int i = 0; i < eventLinks.size(); i++){
			EventLinkRecord rec = (EventLinkRecord) eventLinks.get(i);
			if (rec.getEventType().isInstance(event))
				rec.dispatchEvent(event);
		}
	}

	public void removeEventLink(Class<? extends IEvent<?>> eventType, Object target) {
		if (eventLinks == null)
			return;
		
		for (int i = 0; i < eventLinks.size(); i++){
			EventLinkRecord rec = (EventLinkRecord) eventLinks.get(i);
			
			if (rec.getEventType() == eventType){
				rec.removeLink(target);
				return;
			}
		}
	}

	
}
