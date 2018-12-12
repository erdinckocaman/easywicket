package com.tamplan.wicket.easywicket.event;

import java.util.Objects;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public abstract class WicketAjaxEvent extends WicketEvent {

	private AjaxRequestTarget requestTarget;

	public WicketAjaxEvent(Component source, AjaxRequestTarget requestTarget) {
		super(source);
		
		Objects.requireNonNull(requestTarget);

		this.requestTarget = requestTarget;
	}

	public AjaxRequestTarget getRequestTarget() {
		return requestTarget;
	}

}
