package com.tamplan.wicket.easywicket.common;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.apache.wicket.markup.html.form.AbstractChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;

public class EasyChoiceRenderer implements IChoiceRenderer<Serializable>{
	
	private static final long serialVersionUID = 1L;
	
	private EasyWicket annot;
	private AbstractChoice<?, ?> choiceComponent;
	private static final EasyWicketUtil util;
	
	static {
		util = EasyWicketUtil.getInstance();
	}

	public EasyChoiceRenderer(final EasyWicket annot, AbstractChoice<?, ?> choiceComponent) {
		Objects.requireNonNull(annot);
		Objects.requireNonNull(choiceComponent);
		
		this.annot = annot;
		this.choiceComponent = choiceComponent;
	}

	public Object getDisplayValue(Serializable object) {
		if (annot.displayProperty() == null || annot.displayProperty().length() == 0) {
			return object;
		}

		if (util.hasGetterProperty(object, annot.displayProperty())) {
			return util.getValue(object, annot.displayProperty());
		} else {
			IEasyWicketContainer container = util.findContainer(choiceComponent);

			WidgetContext widgetContext = new WidgetContext();
			widgetContext.setObject(object);
			container.setCurrentWidgetContext(widgetContext);

			container.setCurrentWidgetContext(widgetContext);
			return util.getValue(container, annot.displayProperty());
		}
	}

	public String getIdValue(Serializable object, int index) {
		if (annot.idProperty() == null || annot.idProperty().length() == 0) {
			return String.valueOf(index);
		}

		if (util.hasGetterProperty(object, annot.idProperty())) {
			Object val = util.getValue(object, annot.idProperty());
			
			if (val == null) {
				return null;
			} else {
				return val.toString();
			}
		} else {
			IEasyWicketContainer container = util.findContainer(choiceComponent);

			WidgetContext widgetContext = new WidgetContext();
			widgetContext.setObject(object);
			container.setCurrentWidgetContext(widgetContext);

			return util.getValue(container, annot.idProperty());
		}
	}

	@Override
	public Serializable getObject(String id, IModel<? extends List<? extends Serializable>> choices) {
		List<? extends Serializable> allChoices = choices.getObject();
		
		for (int index = 0; index < allChoices.size(); index++) {
			// Get next choice
			final Serializable choice = allChoices.get(index);
			if (getIdValue(choice, index).equals(id)) {
				return choice;
			}
		}
		return null;
	}
}
