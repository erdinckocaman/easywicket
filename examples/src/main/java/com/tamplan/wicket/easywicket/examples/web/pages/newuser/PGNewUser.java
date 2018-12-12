package com.tamplan.wicket.easywicket.examples.web.pages.newuser;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;

public class PGNewUser extends WebPage implements IEasyWicketContainer {

	private static final long serialVersionUID = 1L;

	@EasyWicket(id = "form")
	Form<Void> form;

	@EasyWicket(id = "form.txtName", value = "name")
	TextField<String> txtName;

	@EasyWicket(id = "form.txtSurname", value = "surname")
	TextField<String> txtSurname;

	@EasyWicket(id = "form.countrySelection", value = "selectedCountry", list = "countryList", idProperty = "name", displayProperty = "name")
	DropDownChoice<Country> countrySelection;

	@EasyWicket(id = "form.btnSubmit", action = "actionSubmit")
	Button btnSubmit;

	private static Logger logger = LoggerFactory.getLogger(PGNewUser.class);
	private List<Country> countryList;
	private Country selectedCountry;
	private String name, surname;

	public void initContainer() {
		countryList = Arrays.asList(Country.values());
		selectedCountry = countryList.get(0);
	}

	public void packContainer() {
	}

	public void setCurrentWidgetContext(WidgetContext widgetContext) {
	}

	public void actionSubmit() {
		logger.info("name={} surname={}, selected country={}", name, surname, selectedCountry);
	}
}
