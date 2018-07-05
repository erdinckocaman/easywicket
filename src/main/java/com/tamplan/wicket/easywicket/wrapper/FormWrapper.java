package com.tamplan.wicket.easywicket.wrapper;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IFormSubmitter;

import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.IEasyWicketWrapper;
import com.tamplan.wicket.easywicket.event.EventSource;
import com.tamplan.wicket.easywicket.event.IEvent;
import com.tamplan.wicket.easywicket.event.IEventSource;
import com.tamplan.wicket.easywicket.web.event.FormAfterProcessEvent;
import com.tamplan.wicket.easywicket.web.event.FormBeforeProcessEvent;
import com.tamplan.wicket.easywicket.web.event.FormOnValidateEvent;

public class FormWrapper implements IEasyWicketWrapper {

	private static class EasyForm extends Form<Void> implements IEventSource {

		private EventSource eventSource;

		public EasyForm(String id) {
			super(id);

			eventSource = new EventSource();
		}

		private static final long serialVersionUID = 1L;

		public void addEventLink(Class<? extends IEvent<?>> eventType, Serializable target) {
			eventSource.addEventLink(eventType, target);
		}

		public void addEventLink(Class<? extends IEvent<?>> eventType, Serializable target, String method) {

			eventSource.addEventLink(eventType, target, method);
		}

		public void removeEventLink(Class<? extends IEvent<?>> eventType, Serializable target) {
			eventSource.removeEventLink(eventType, target);
		}

		public void dispatchEvent(IEvent<?> event) {
			eventSource.dispatchEvent(event);
		}

		@Override
		protected void onValidate() {
			eventSource.dispatchEvent(new FormOnValidateEvent(this));
		}

		@Override
		public void process(IFormSubmitter submittingComponent) {
			eventSource.dispatchEvent(new FormBeforeProcessEvent(this, submittingComponent));
			try {
				super.process(submittingComponent);
			} finally {
				eventSource.dispatchEvent(new FormAfterProcessEvent(this, submittingComponent));
			}
		}

	}

	public Component create(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {

		return new EasyForm(widgetId);

	}

}
