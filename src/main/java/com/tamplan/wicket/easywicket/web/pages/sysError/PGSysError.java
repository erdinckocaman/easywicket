package net.sourceforge.easywicket.web.pages.sysError;

import net.sourceforge.easywicket.web.common.EasyPage;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;


public class PGSysError extends EasyPage {
	
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
