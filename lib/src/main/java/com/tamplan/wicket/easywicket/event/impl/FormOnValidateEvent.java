package com.tamplan.wicket.easywicket.event.impl;

import org.apache.wicket.Component;

import com.tamplan.wicket.easywicket.event.WicketEvent;

public class FormOnValidateEvent extends WicketEvent {

	public FormOnValidateEvent(Component source) {
		super(source);
	}
}
