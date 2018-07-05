package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;

public class EventForwarder implements Serializable{

	private static final long serialVersionUID = 1L;

	public void forwardEvent(IEventSource src, Class<? extends IEvent<?>> eventType, final IEventSource target) {
		src.addEventLink(eventType, new Serializable() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unused")
			private void processEvent(IEvent<?> event) {
				target.dispatchEvent(event);
			}
		});
	}
	
	 
}
