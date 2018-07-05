package com.tamplan.wicket.easywicket;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;


public class EasyWicketModel<T> implements IModel<T> {
	
	private static final long serialVersionUID = 1L;

	private String expression;
	
	private transient T value;
	private transient boolean valueGet;

	private Component component;
	
	private EasyWicketUtil util;
	

	public EasyWicketModel(Component component, String expression) {
		this.expression = expression;
		this.component = component;
		this.util = EasyWicketUtil.getInstance();
		
		valueGet = false;
		
	}

	public T getObject() {
		if ( valueGet ) {
			//TODO
			// bazi durumlarda degerler degisse bile farkedilmiyor
			// bu yuzden cachingi iptal ettim.
			//return value;
			
		}

		IEasyWicketContainer container = getContainer(component);
		
		PropertyModel propertyModel = new PropertyModel(container, expression);
		
		value = (T)propertyModel.getObject();
		
		valueGet = true;
		return value;
	}

	public void setObject(Object value) {
		IEasyWicketContainer container = getContainer(component);
		
		PropertyModel propertyModel = new PropertyModel(container, expression);
		propertyModel.setObject(value);
		
		valueGet = false;
	}
	
	private IEasyWicketContainer getContainer(Component component) {
		IEasyWicketContainer container = null;
		
//		if ( component instanceof IEasyWicketContainer ) {
//			container = (IEasyWicketContainer) component;
//		}else {
//			container = component.findParent(IEasyWicketContainer.class);
//		}
		
		container = component.findParent(IEasyWicketContainer.class);
		
		if ( container == null ) {
			throw new IllegalStateException("No " + IEasyWicketContainer.class + 
					" type of container found for component=" + component);
		}
		
		return container;
		

	}

	public void detach() {
		value = null;
		valueGet = false;
	}
	


}
