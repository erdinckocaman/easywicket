package net.sourceforge.easywicket.web.pages.pageExpired;

import net.sourceforge.easywicket.web.common.EasyPage;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;


public class PGPageExpired extends EasyPage {

	public PGPageExpired() {
		add(new BookmarkablePageLink("lnkHomePage", getApplication().getHomePage()));
	}
}
