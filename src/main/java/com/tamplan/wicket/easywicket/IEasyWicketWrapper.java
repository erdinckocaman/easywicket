package com.tamplan.wicket.easywicket;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

public interface IEasyWicketWrapper {

	public Component create(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget);
}
