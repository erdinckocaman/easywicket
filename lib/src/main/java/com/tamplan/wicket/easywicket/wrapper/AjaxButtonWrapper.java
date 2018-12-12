package com.tamplan.wicket.easywicket.wrapper;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.string.Strings;

import com.tamplan.wicket.easywicket.BaseWrapper;
import com.tamplan.wicket.easywicket.EasyWicket;
import com.tamplan.wicket.easywicket.EasyWicketUtil;
import com.tamplan.wicket.easywicket.IEasyWicketContainer;
import com.tamplan.wicket.easywicket.WidgetContext;
import com.tamplan.wicket.easywicket.event.EventHandler;
import com.tamplan.wicket.easywicket.event.EventSource;
import com.tamplan.wicket.easywicket.event.IEvent;
import com.tamplan.wicket.easywicket.event.IEventSource;
import com.tamplan.wicket.easywicket.event.impl.AjaxFormValidatonErrorEvent;
import com.tamplan.wicket.easywicket.event.impl.UpdateAjaxAttributesEvent;

public class AjaxButtonWrapper extends BaseWrapper {

	private static final long serialVersionUID = 1L;

	private static class EasyAjaxButton extends AjaxButton implements IEventSource{

		private static final long serialVersionUID = 1L;
		private EasyWicket annot;
		private static final EasyWicketUtil util;
		static {
			util = EasyWicketUtil.getInstance();
		}
		
		private EventSource eventSource;

		public EasyAjaxButton(String id, EasyWicket annot) {
			super(id);
			this.annot = annot;
			
			eventSource = new EventSource();

		}

		@Override
		protected void onSubmit(AjaxRequestTarget target) {
			if (!Strings.isEmpty(annot.action())) {
				IEasyWicketContainer parentComponent = util.findContainer(this);
				
				WidgetContext widgetContext = new WidgetContext();
				widgetContext.setAjaxRequestTarget(target);
				
				parentComponent.setCurrentWidgetContext(widgetContext);
				
				util.callAction(util.findContainer(this), annot.action());
			}
		}

		@Override
		protected void onError(AjaxRequestTarget target) {
			AjaxFormValidatonErrorEvent event = new AjaxFormValidatonErrorEvent(this, target);

			send(WebApplication.get(), Broadcast.EXACT, event);
		}
		
		@Override
		protected void onConfigure() {
			super.onConfigure();
			util.configureComponent(this, annot.visible(), annot.enabled());
		}
		
		@Override
		protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
			super.updateAjaxAttributes(attributes);
			
			dispatchEvent(new UpdateAjaxAttributesEvent(this, attributes));
			
		}

		@Override
		public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target) {
			eventSource.addEventLink(eventType, target);

		}

		@Override
		public <T extends IEvent<?>> void addEventLink(Class<T> eventType, Serializable target, String method) {
			eventSource.addEventLink(eventType, target, method);

		}

		@Override
		public <T extends IEvent<?>> void addEventLink(Class<T> eventType, EventHandler<T> eventHandler) {
			eventSource.addEventLink(eventType, eventHandler);

		}

		@Override
		public <T extends IEvent<?>> void removeEventLink(Class<T> eventType, Serializable target) {
			eventSource.removeEventLink(eventType, target);

		}

		@Override
		public <T extends IEvent<?>> void dispatchEvent(T event) {
			eventSource.dispatchEvent(event);

		}
		
		

	}

	@Override
	protected Component createInstance(String widgetId, Class<? extends Component> widgetClass, EasyWicket annot,
			MarkupContainer parentWidget) {

		return new EasyAjaxButton(widgetId, annot);
	}

}
