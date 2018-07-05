package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;

public class ButtonWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {

		return new Button(widgetId) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				if (!Strings.isEmpty(annot.action())) {
					util.callAction(util.findContainer(this), annot.action());
				}

			}

			@Override
			protected void onConfigure() {
				super.onConfigure();

				util.configureComponent(this, annot.visible(), annot.enabled());
			}

		};

	}

}
