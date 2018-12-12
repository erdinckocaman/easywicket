package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.event.impl.AjaxFormValidatonErrorEvent;

public class AjaxSubmitLinkWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {

		return new AjaxSubmitLink(widgetId) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				if (!Strings.isEmpty(annot.action())) {
					util.callAction(util.findContainer(this), annot.action());
				}
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
				AjaxFormValidatonErrorEvent event = new AjaxFormValidatonErrorEvent(this, target);

				send(WebApplication.get(), Broadcast.EXACT, event);
			}
		};
	}
}
