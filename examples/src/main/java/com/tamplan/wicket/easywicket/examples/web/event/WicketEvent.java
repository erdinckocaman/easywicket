package com.tamplan.wicket.easywicket.examples.web.event;

import org.apache.wicket.Component;

import com.tamplan.wicket.easywicket.event.BaseEvent;

public abstract class WicketEvent extends BaseEvent<Component> {

	public WicketEvent(Component source) {
		super(source);
	}

}
