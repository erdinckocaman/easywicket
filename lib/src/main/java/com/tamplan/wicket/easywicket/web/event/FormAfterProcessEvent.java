package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IFormSubmitter;

public class FormAfterProcessEvent extends WicketEvent {

	private IFormSubmitter formSubmitter;

	public FormAfterProcessEvent(Component source, IFormSubmitter formSubmitter) {
		super(source);

		this.formSubmitter = formSubmitter;
	}

	public IFormSubmitter getFormSubmitter() {
		return formSubmitter;
	}
}
