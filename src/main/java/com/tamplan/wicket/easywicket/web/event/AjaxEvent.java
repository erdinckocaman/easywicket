package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public abstract class AjaxEvent extends WicketEvent {

	private AjaxRequestTarget requestTarget;

	public AjaxEvent(Component source, AjaxRequestTarget requestTarget) {
		super(source);
		
		if ( requestTarget == null ) {
			throw new IllegalArgumentException("Ajax request is null !");
		}
		
		this.requestTarget = requestTarget;
	}
	
	public AjaxRequestTarget getRequestTarget() {
		return requestTarget;
	}

}
