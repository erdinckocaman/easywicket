package com.tamplan.wicket.easywicket.web.event;

import java.util.Objects;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;

public class UpdateAjaxAttributesEvent extends WicketEvent {

	private AjaxRequestAttributes ajaxRequestAttributes;

	public UpdateAjaxAttributesEvent(Component source, AjaxRequestAttributes ajaxRequestAttributes) {
		super(source);
		Objects.requireNonNull(ajaxRequestAttributes);
		
		this.ajaxRequestAttributes = ajaxRequestAttributes;
	}

	public AjaxRequestAttributes getAjaxRequestAttributes() {
		return ajaxRequestAttributes;
	}
}
