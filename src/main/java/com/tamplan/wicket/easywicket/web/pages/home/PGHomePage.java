package com.tamplan.wicket.easywicket.web.pages.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.web.common.EasyPage;

public class PGHomePage extends EasyPage implements IEasyWicketContainer {

	private static final long serialVersionUID = 1L;

	@EasyWicket(id = "form")
	Form<Void> form;

	@EasyWicket(id = "form.txtUsername", value = "username")
	Label txtUsername;
	String username;

	@EasyWicket(id = "form.txtPassword", value = "password", required = true)
	TextField<String> txtPassword;
	String password;

	@EasyWicket(id = "form.btnSubmit", action = "actionSubmit")
	Button btnSubmit;

	@EasyWicket(id = "form.ddSelect", list = "selectList", value = "selected")
	DropDownChoice ddSelect;

	private String selected;

	public PGHomePage() {

	}

	public List getSelectList() {
		List list = new ArrayList();
		list.add("erdinc");
		list.add("kocaman");
		return list;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getSelected() {
		return selected;
	}

	public void pack() {
		add(new FeedbackPanel("feedbackPanel"));
	}

	private void actionSubmit() {
		System.out.println("action called");
	}

	public void setCurrentWidgetContext(WidgetContext widgetContext) {
	}

	public void initValues() {
	}

}
