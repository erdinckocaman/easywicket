package com.tamplan.wicket.easywicket.wrapper;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;

import net.sourceforge.easywicket.web.event.AjaxFormValidatonErrorEvent;

public class AjaxSubmitLinkWrapper extends BaseWrapper{

	private static final long serialVersionUID = 1L;

	@Override
	protected Component createInstance(String widgetId,
			Class<? extends Component> widgetClass, final EasyWicket annot,
			MarkupContainer parentWidget) {
		
		
		AjaxSubmitLink link = new AjaxSubmitLink(widgetId) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if ( !Strings.isEmpty(annot.action())) {
					util.callAction(util.findContainer(this), annot.action());
				}
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				AjaxFormValidatonErrorEvent event = new AjaxFormValidatonErrorEvent(this, target);
				event.setForm(form);
										
				send(WebApplication.get(), Broadcast.EXACT, event);
			}
		};
		
		return link;
	}

}
