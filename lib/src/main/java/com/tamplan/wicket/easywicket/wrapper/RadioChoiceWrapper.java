package com.tamplan.wicket.easywicket.wrapper;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.RadioChoice;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketModel;
import com.tamplan.wicket.easywicket.common.EasyChoiceRenderer;

public class RadioChoiceWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {

		RadioChoice<Serializable> radioChoice = (RadioChoice<Serializable>) util.createInstance(widgetClass, widgetId);
		
		radioChoice.setChoiceRenderer(new EasyChoiceRenderer(annot, radioChoice));
		
		radioChoice.setChoices(new EasyWicketModel(radioChoice, annot.list()));
		
		return radioChoice;
	}
}
