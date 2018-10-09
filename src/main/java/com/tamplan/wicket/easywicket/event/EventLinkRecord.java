package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Source code is based on event handling code of buoy swing
 * library(buoy.sourceforge.net)
 * 
 *
 */
public class EventLinkRecord<T extends IEvent<?>>  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static class EventLinkDetails<T extends IEvent<?>> implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Serializable target;
		private String targetMethodName;
		private EventHandler<T> handler;
	}
	
	private Class<T> eventClass;
	private List<EventLinkDetails<T>> handlerList;

	/**
	 * Create an EventLinkRecord for storing links for a particular event class.
	 */

	public EventLinkRecord(Class<T> eventType) {
		Objects.requireNonNull(eventType);
		
		eventClass = eventType;
		
		handlerList = new LinkedList<>();
	}

	/**
	 * Get the event class for this record.
	 */

	public Class<?> getEventType() {
		return eventClass;
	}

	public void addLink(Serializable target, String methodName) {
		Objects.requireNonNull(target);
		Objects.requireNonNull(methodName);
		
		if ( methodName == null || methodName.trim().length() == 0 ) {
			throw new IllegalArgumentException("Method name is empty!");
		}
		
		EventLinkDetails<T> details = new EventLinkDetails<>();
		details.target = target;
		details.targetMethodName = methodName;
		
		handlerList.add(details);

	}
	
	public void addLink(EventHandler<T> eventHandler) {
		Objects.requireNonNull(eventHandler);
		
		EventLinkDetails<T> details = new EventLinkDetails<>();
		details.handler = eventHandler;
		
		handlerList.add(details);
	}

	/**
	 * Remove an object from the list of targets to be notified of events of this
	 * type.
	 *
	 * @param target
	 *            the target object to remove
	 */

	public void removeLink(Serializable target) {
		Objects.requireNonNull(target);
		
		List<EventLinkDetails<T>> linksToRemove = handlerList.stream().filter(eventLinkDetails->eventLinkDetails.target != null).filter(eventLinkDetails-> eventLinkDetails.target.equals(target)).collect(Collectors.toList());
		
		handlerList.removeAll(linksToRemove);
	}

	/**
	 * Send an event to every target which has been added to this record.
	 */
	public void dispatchEvent(T event) {
		Objects.requireNonNull(event);
		
		if ( !event.getClass().equals(eventClass)) {
			throw new IllegalArgumentException("Event is not type of required event class, event class at hand=" +
					event.getClass() + " required event class=" + eventClass);
		}
		
		handlerList.forEach(eventLinkDetails-> {
			if ( eventLinkDetails.handler != null ) {
				eventLinkDetails.handler.handle(event);
			}else {
				Object targetObject = eventLinkDetails.target;
				String targetObjectMethod = eventLinkDetails.targetMethodName;
				
				try {
					callMethod(targetObject, targetObjectMethod, event);
				} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	private void callMethod(Object targetObject, String targetObjectMethod, IEvent<?> event)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

		Class<?> cls = targetObject.getClass();

		while (cls != null) {
			Method[] m = cls.getDeclaredMethods();

			for (int i = 0; i < m.length; i++)
				if (m[i].getName().equals(targetObjectMethod)) {
					Class<?>[] param = m[i].getParameterTypes();

					if (param.length == 0 || (param.length == 1 && param[0].isAssignableFrom(eventClass))) {
						Method methodFound = m[i];
						methodFound.setAccessible(true);
						methodFound.invoke(targetObject, event);
						return;
					}
				}
			cls = cls.getSuperclass();
		}
		throw new NoSuchMethodException(targetObjectMethod);
	}
}
