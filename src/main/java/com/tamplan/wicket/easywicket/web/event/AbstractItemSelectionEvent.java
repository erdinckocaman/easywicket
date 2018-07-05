package com.tamplan.wicket.easywicket.web.event;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public abstract class AbstractItemSelectionEvent<T> extends AjaxEvent {

	private T targetItem;

	public AbstractItemSelectionEvent(Component source, AjaxRequestTarget requestTarget) {

		super(source, requestTarget);
	}

	public AbstractItemSelectionEvent(Component source, AjaxRequestTarget requestTarget, T targetItem) {
		this(source, requestTarget);

		setTargetItem(targetItem);
	}

	@Override
	public String toString() {
		return "[source=" + getSource() + " target item=" + targetItem + "]";
	}

	public void setTargetItem(T targetItem) {
		this.targetItem = targetItem;
	}

	public T getTargetItem() {
		return targetItem;
	}
}
