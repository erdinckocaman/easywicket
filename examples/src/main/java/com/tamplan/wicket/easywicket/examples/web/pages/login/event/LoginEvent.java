package com.tamplan.wicket.easywicket.examples.web.pages.login.event;

import org.apache.wicket.Component;

import com.tamplan.wicket.easywicket.event.WicketEvent;

public class LoginEvent extends WicketEvent {

	private boolean success;

	public LoginEvent(Component source) {
		super(source);
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
}
