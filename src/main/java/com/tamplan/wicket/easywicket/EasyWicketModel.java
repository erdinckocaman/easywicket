package com.tamplan.wicket.easywicket;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class EasyWicketModel<T> implements IModel<T> {

	private static final long serialVersionUID = 1L;

	private String expression;

	private transient T value;
	private transient boolean valueSet;

	private Component component;

	public EasyWicketModel(Component component, String expression) {
		this.expression = expression;
		this.component = component;

		valueSet = false;
	}

	@Override
	public T getObject() {
		if ( valueSet ) {
			return value;
		}

		IEasyWicketContainer container = getContainer(component);

		PropertyModel<T> propertyModel = new PropertyModel<>(container, expression);

		value = propertyModel.getObject();

		valueSet = true;

		return value;
	}

	@Override
	public void setObject(T value) {
		IEasyWicketContainer container = getContainer(component);

		PropertyModel<T> propertyModel = new PropertyModel<>(container, expression);
		propertyModel.setObject(value);

		valueSet = false;
	}

	private IEasyWicketContainer getContainer(Component component) {
		IEasyWicketContainer container = null;

		container = component.findParent(IEasyWicketContainer.class);

		if (container == null) {
			throw new IllegalStateException("No parent container found for component=" + component);
		}

		return container;
	}

	@Override
	public void detach() {
		value = null;
		valueSet = false;
	}

}
