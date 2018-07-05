package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;
import com.tamplan.wicket.easywicket.web.event.AjaxFormValidatonErrorEvent;

public class AjaxButtonWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	private static class EasyAjaxButton extends AjaxButton {

		private static final long serialVersionUID = 1L;
		private EasyWicket annot;
		private final static EasyWicketUtil util;
		static {
			util = EasyWicketUtil.getInstance();
		}

		public EasyAjaxButton(String id, EasyWicket annot) {
			super(id);
			this.annot = annot;
		}

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

	}

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {

		return new EasyAjaxButton(widgetId, annot);
	}

}
