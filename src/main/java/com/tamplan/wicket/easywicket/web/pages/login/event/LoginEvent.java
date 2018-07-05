package net.sourceforge.easywicket.web.pages.login.event;

import org.apache.wicket.Component;

import net.sourceforge.easywicket.web.event.WicketEvent;

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
