package net.sourceforge.easywicket.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class WebContextListener
 *
 */
public class WebContextListener implements ServletContextListener {

	private static Logger logger = LoggerFactory.getLogger(WebContextListener.class);
	
    public WebContextListener() {
       
    }

	public void contextDestroyed(ServletContextEvent event) {
		String servletContextName = event.getServletContext().getServletContextName();

		try {
			onBeforeContextDestroyed(event);
		}catch(Exception e) {
			logger.error("Error while destroying servlet context=" +  servletContextName, e);
		}
		logger.error("==========================================================");
		logger.error("Servlet Context Destroyed=" + servletContextName);
		logger.error("==========================================================");

	}


	public void contextInitialized(ServletContextEvent event) {
		String servletContextName = event.getServletContext().getServletContextName();

		try {
			onContextInit(event);
			
			logger.error("==========================================================");
			logger.error("Servlet Context Initialized=" + servletContextName);
			logger.error("==========================================================");
			
		}catch(Exception e) {
			logger.error("==========================================================");
			logger.error("Error while initing servlet context=" + servletContextName, e);
			logger.error("==========================================================");
			
			throw new RuntimeException(e);
		}
		

	}
	
	
	private void onContextInit(ServletContextEvent event) {
		
	}

	private void onBeforeContextDestroyed(ServletContextEvent event) {
		
	}

	
}
