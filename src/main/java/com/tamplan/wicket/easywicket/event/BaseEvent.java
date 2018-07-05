package com.tamplan.wicket.easywicket.event;

public abstract class BaseEvent<T> implements IEvent<T> {
	
	private T source;

	public BaseEvent(T source) {
		if ( source == null ) {
			throw new IllegalArgumentException("source is null ");
		}
		
		this.source = source;
	}
	
	@Override
	public String toString() {
		return "[source=" + source + "]";
	}
	
	public T getSource() {
		return source;
	}

}
