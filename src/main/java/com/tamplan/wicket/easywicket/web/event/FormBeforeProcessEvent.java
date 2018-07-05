package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IFormSubmitter;

public class FormBeforeProcessEvent extends WicketEvent {

	IFormSubmitter formSubmitter;

	public FormBeforeProcessEvent(Component source, IFormSubmitter formSubmitter) {
		super(source);

		this.formSubmitter = formSubmitter;
	}

	public IFormSubmitter getFormSubmitter() {
		return formSubmitter;
	}

}
