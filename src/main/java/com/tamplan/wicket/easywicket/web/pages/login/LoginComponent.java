package net.sourceforge.easywicket.web.pages.login;

import net.sourceforge.easywicket.EasyWicket;
import net.sourceforge.easywicket.web.common.EasyPanel;
import net.sourceforge.easywicket.web.pages.login.event.LoginEvent;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginComponent extends EasyPanel {

	@EasyWicket(id="form")
	Form<Void> form;
	
	@EasyWicket(id="form.txtUsername", value="username", required=true)
	TextField<String>txtUsername;
	
	@EasyWicket(id="form.txtPassword", value="password", required=true)
	PasswordTextField txtPassword;
	
	@EasyWicket(id="form.btnLogin", action="actionLogin")
	Button btnLogin;
	
	String username, password;
	
	private static Logger logger = LoggerFactory.getLogger(LoginComponent.class);
	
	public LoginComponent(String id) {
		super(id);
		
	}
	
	public void actionLogin() {
		if ( logger.isInfoEnabled() ) {
			logger.info("username=" + username + " password=" + password);
		}
		
	
		LoginEvent event = new LoginEvent(this);
		event.setSuccess(System.currentTimeMillis() % 2 == 0);
		dispatchEvent(event);
	}

}
