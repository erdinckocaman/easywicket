package com.tamplan.wicket.easywicket.examples.web;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

public class WebProjectTemplate1RequestCycle implements IRequestCycleListener {

	public void onBeginRequest(RequestCycle requestCycle) {
	}

	public void onDetach(RequestCycle requestCycle) {
	}

	public void onEndRequest(RequestCycle requestCycle) {
	}

	public IRequestHandler onException(RequestCycle requestCycle, Exception exception) {
		return null;
	}

	public void onExceptionRequestHandlerResolved(RequestCycle requestCycle, IRequestHandler requestHandler,
			Exception exception) {
	}

	public void onRequestHandlerExecuted(RequestCycle requestCycle, IRequestHandler requestHandler) {
	}

	public void onRequestHandlerResolved(RequestCycle requestCycle, IRequestHandler requestHandler) {
	}

	public void onRequestHandlerScheduled(RequestCycle requestCycle, IRequestHandler requestHandler) {
	}

	public void onUrlMapped(RequestCycle requestCycle, IRequestHandler requestHandler, Url url) {
	}

}
