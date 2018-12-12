package com.tamplan.wicket.easywicket.examples.web.pages.login;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.common.EasyPanel;
import com.tamplan.wicket.easywicket.examples.web.pages.login.event.LoginEvent;

public class LoginComponent extends EasyPanel {

	private static final long serialVersionUID = 1L;

	@EasyWicket(id = "form")
	Form<Void> form;

	@EasyWicket(id = "form.txtUsername", value = "username", required = true)
	TextField<String> txtUsername;

	@EasyWicket(id = "form.txtPassword", value = "password", required = true)
	PasswordTextField txtPassword;

	@EasyWicket(id = "form.btnLogin", action = "actionLogin")
	Button btnLogin;

	String username, password;

	private static Logger logger = LoggerFactory.getLogger(LoginComponent.class);

	public LoginComponent(String id) {
		super(id);
	}

	public void actionLogin() {
		logger.info("username={}", username);

		LoginEvent event = new LoginEvent(this);
		event.setSuccess(System.currentTimeMillis() % 2 == 0);
		dispatchEvent(event);
	}

}
