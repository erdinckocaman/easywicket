package com.tamplan.wicket.easywicket.event;

import org.apache.wicket.Component;

public abstract class WicketEvent extends BaseEvent<Component> {

	public WicketEvent(Component source) {
		super(source);
	}

}
