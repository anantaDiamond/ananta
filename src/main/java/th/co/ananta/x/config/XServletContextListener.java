package th.co.ananta.x.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class XServletContextListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(XServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("The application started");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		log.info("The application stopped");
	}
}
