package com.tamplan.wicket.easywicket;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class WidgetContext implements Serializable{

	private static final long serialVersionUID = 1L;
	private Serializable object;
	private AjaxRequestTarget ajaxRequestTarget;

	public void setObject(Serializable object) {
		this.object = object;
	}

	public <T> T getObject() {
		return (T) object;
	}

	public void setAjaxRequestTarget(AjaxRequestTarget ajaxRequestTarget) {
		this.ajaxRequestTarget = ajaxRequestTarget;
	}

	public AjaxRequestTarget getAjaxRequestTarget() {
		return ajaxRequestTarget;
	}

}
