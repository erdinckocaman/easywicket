package net.sourceforge.easywicket.web;

import net.sourceforge.easywicket.EasyWicketComponentInitializer;
import net.sourceforge.easywicket.EasyWicketComponentListener;
import net.sourceforge.easywicket.web.pages.home.PGHomePage;
import net.sourceforge.easywicket.web.pages.login.LoginPage;
import net.sourceforge.easywicket.web.pages.newUser.PGNewUser;
import net.sourceforge.easywicket.web.pages.pageExpired.PGPageExpired;
import net.sourceforge.easywicket.web.pages.sysError.PGSysError;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IExceptionSettings;

public class WebProjectTemplate1WebApp extends WebApplication {
	
	@Override
	public Class<? extends Page> getHomePage() {
		return PGHomePage.class;
	}
	
	@Override
	protected void init() {
		super.init();
		
		initMarkupConfig();
		initExceptionPages();
		initLogging();
		initMounts();
		
		//initRequestCycle();
		initCompInit();
	}



	// -------------------------------------------------------------------------
	// Initialization
	// -------------------------------------------------------------------------
	private void initLogging() {
	}
	
	private void initRequestCycle() {
		getRequestCycleListeners().add(new WebProjectTemplate1RequestCycle());

	}

	private void initExceptionPages() {
		getApplicationSettings().setInternalErrorPage(PGSysError.class);
		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		getApplicationSettings().setPageExpiredErrorPage(PGPageExpired.class);
	}

	private void initMarkupConfig() {
		getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
	}
	
	private void initMounts() {
		mountPage("/newUser", PGNewUser.class);
		mountPage("/login", LoginPage.class);
	}
	
	private void initCompInit() {
		getComponentInitializationListeners().add(new EasyWicketComponentInitializer());
	}
	
	// -------------------------------------------------------------------------
	// Overrides
	// -------------------------------------------------------------------------
//	@Override
	public Session newSession(Request request, Response response) {
		return new WebProjectTemplate1Session(request);
	}
	
}
