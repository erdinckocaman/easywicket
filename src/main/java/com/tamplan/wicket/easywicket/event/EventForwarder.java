package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;

public class EventForwarder implements Serializable{

	private static final long serialVersionUID = 1L;

	public <T extends IEvent<?>> void forwardEvent(IEventSource src, Class<T> eventType, final IEventSource target) {
		src.addEventLink(eventType, new Serializable() {
			
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			private void processEvent(T event) {
				target.dispatchEvent(event);
			}
		});
	}
	 
}
