package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;

public class LabelWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	private EasyWicketUtil util;

	public LabelWrapper() {
		util = EasyWicketUtil.getInstance();
	}

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {

		return util.createInstance(widgetClass, widgetId);
	}
}
