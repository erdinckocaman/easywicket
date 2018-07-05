package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class ItemToBeInsertedEvent extends AjaxEvent {

	public ItemToBeInsertedEvent(Component source, AjaxRequestTarget requestTarget) {
		super(source, requestTarget);
	}

}
