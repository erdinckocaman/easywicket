package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;

public class WebMarkupContainerWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId,
			Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {
		
		WebMarkupContainer container = new WebMarkupContainer(widgetId) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onConfigure() {
				util.configureComponent(this, annot.visible(), annot.enabled());
			}

		};
		return container;
	}

}
