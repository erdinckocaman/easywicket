package com.tamplan.wicket.easywicket.event.impl;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.tamplan.wicket.easywicket.event.WicketAjaxEvent;

public class AjaxFormValidatonErrorEvent extends WicketAjaxEvent {

	private Form<?> form;

	public AjaxFormValidatonErrorEvent(Component source, AjaxRequestTarget requestTarget) {
		super(source, requestTarget);
	}

	public void setForm(Form<?> form) {
		this.form = form;
	}

	public Form<?> getForm() {
		return form;
	}

}
