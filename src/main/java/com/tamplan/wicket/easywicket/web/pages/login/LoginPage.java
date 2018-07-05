package net.sourceforge.easywicket.web.pages.login;

import net.sourceforge.easywicket.web.common.EasyPage;
import net.sourceforge.easywicket.web.pages.login.event.LoginEvent;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class LoginPage extends EasyPage {

	public LoginPage() {
		add(new FeedbackPanel("feedbackPanel"));
		
		LoginComponent loginComponent = new LoginComponent("loginPanel");
		add(loginComponent);
		
		
		loginComponent.addEventLink(LoginEvent.class, this, "onLogin");
	}
	
	@SuppressWarnings("unused")
	private void onLogin(LoginEvent event) {
		if ( event.isSuccess() ) {
			// redirect to another page
			info("Login successful");
		}else {
			error("Login failed");
		}
	}
}
