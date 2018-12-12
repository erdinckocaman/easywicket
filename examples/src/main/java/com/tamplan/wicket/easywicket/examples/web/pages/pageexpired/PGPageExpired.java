package com.tamplan.wicket.easywicket.examples.web.pages.pageexpired;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

import com.tamplan.wicket.easywicket.web.common.EasyPage;

public class PGPageExpired extends EasyPage {

	private static final long serialVersionUID = 1L;

	public PGPageExpired() {
		add(new BookmarkablePageLink<Void>("lnkHomePage", getApplication().getHomePage()));
	}
}
