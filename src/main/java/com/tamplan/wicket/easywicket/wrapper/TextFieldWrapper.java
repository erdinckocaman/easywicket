package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.TextField;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;

public class TextFieldWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {

		return new TextField<Void>(widgetId) {
			private static final long serialVersionUID = 1L;

			 @Override
			protected void onConfigure() {
				super.onConfigure();
				
				util.configureComponent(this, annot.visible(), annot.enabled());
			}

		};
	}

}
