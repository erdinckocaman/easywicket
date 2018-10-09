package com.tamplan.wicket.easywicket.event;

@FunctionalInterface
public interface EventHandler<T extends IEvent<?>> {

	public void handle(T event);

}
