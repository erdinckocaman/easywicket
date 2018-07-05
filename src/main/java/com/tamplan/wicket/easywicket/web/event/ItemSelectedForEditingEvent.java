package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class ItemSelectedForEditingEvent<T> extends AbstractItemSelectionEvent<T> {

	public ItemSelectedForEditingEvent(Component source, AjaxRequestTarget requestTarget, T targetItem) {
		super(source, requestTarget, targetItem);
	}
}
