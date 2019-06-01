package th.co.ananta.x.web.base;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener("Session listener for the application")
public class XHttpSessionListener implements javax.servlet.http.HttpSessionListener {

	private static final Logger log = LoggerFactory.getLogger(XHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		log.info("Session created for id " + session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		log.info("Session destroyed for id " + session.getId());
	}

}
