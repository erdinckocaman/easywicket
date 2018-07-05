package net.sourceforge.easywicket.web.event;

import net.sourceforge.easywicket.event.BaseEvent;

import org.apache.wicket.Component;



public abstract class WicketEvent extends BaseEvent<Component> {

	public WicketEvent(Component source) {
		super(source);
	}

}
