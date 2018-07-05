package com.tamplan.wicket.easywicket.web.pages.sysError;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.tamplan.wicket.easywicket.web.common.EasyPage;

public class PGSysError extends EasyPage {

	private static final long serialVersionUID = 1L;

	public PGSysError() {
		add(new BookmarkablePageLink("lnkHomePage", getApplication().getHomePage()));
	}

	@Override
	public boolean isErrorPage() {
		return true;
	}

	@Override
	public boolean isVersioned() {
		return false;
	}
}
