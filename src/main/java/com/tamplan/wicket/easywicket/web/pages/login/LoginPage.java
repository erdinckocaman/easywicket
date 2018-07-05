package com.tamplan.wicket.easywicket.web.pages.login;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.tamplan.wicket.easywicket.web.common.EasyPage;
import com.tamplan.wicket.easywicket.web.pages.login.event.LoginEvent;

public class LoginPage extends EasyPage {

	private static final long serialVersionUID = 1L;

	public LoginPage() {
		add(new FeedbackPanel("feedbackPanel"));

		LoginComponent loginComponent = new LoginComponent("loginPanel");
		add(loginComponent);

		loginComponent.addEventLink(LoginEvent.class, this, "onLogin");
	}

	@SuppressWarnings("unused")
	private void onLogin(LoginEvent event) {
		if (event.isSuccess()) {
			// redirect to another page
			info("Login successful");
		} else {
			error("Login failed");
		}
	}
}
