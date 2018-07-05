package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;

public class AjaxLinkWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {

		return new AjaxLink<Void>(widgetId) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				if (!Strings.isEmpty(annot.action())) {
					WidgetContext ctx = new WidgetContext();
					ctx.setAjaxRequestTarget(target);
					IEasyWicketContainer container = util.findContainer(this);
					container.setCurrentWidgetContext(ctx);

					util.callAction(util.findContainer(this), annot.action());
				}
			}
		};
	}
}
