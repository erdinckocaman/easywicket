package com.tamplan.wicket.easywicket.event;

import java.io.Serializable;

@FunctionalInterface
public interface EventHandler<T extends IEvent<?>> extends Serializable {
	
	public void handle(T event);

}
