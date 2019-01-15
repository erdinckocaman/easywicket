package com.tamplan.wicket.easywicket.wrapper;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketModel;
import com.tamplan.wicket.easywicket.common.EasyChoiceRenderer;

public class DropDownChoiceWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {

		final DropDownChoice<Serializable> dd = (DropDownChoice<Serializable>) super.util.createInstance(widgetClass, widgetId);

		dd.setChoiceRenderer(new EasyChoiceRenderer(annot, dd));

		dd.setChoices(new EasyWicketModel<>(dd, annot.list()));

		return dd;
	}

}
