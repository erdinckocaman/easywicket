package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;

public class FormOnValidateEvent extends WicketEvent {

	public FormOnValidateEvent(Component source) {
		super(source);
	}
}
