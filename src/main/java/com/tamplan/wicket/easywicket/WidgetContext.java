package com.tamplan.wicket.easywicket;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class WidgetContext {

	private Object object;
	private AjaxRequestTarget ajaxRequestTarget;
	
	public void setObject(Object object) {
		this.object = object;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setAjaxRequestTarget(AjaxRequestTarget ajaxRequestTarget) {
		this.ajaxRequestTarget = ajaxRequestTarget;
	}
	
	public AjaxRequestTarget getAjaxRequestTarget() {
		return ajaxRequestTarget;
	}
	
}
