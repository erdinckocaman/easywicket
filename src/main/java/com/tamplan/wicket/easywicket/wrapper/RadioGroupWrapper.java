package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;

public class RadioGroupWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, final EasyWicket annot,
			final MarkupContainer parentWidget) {

		return new RadioGroup(widgetId) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				String visibleStr = annot.visible();

				if (!Strings.isEmpty(visibleStr)) {
					IEasyWicketContainer container = util.findContainer(this);
					return (Boolean) util.getValue(container, visibleStr);
				} else {
					return super.isVisible();
				}
			}
		};

	}
}
