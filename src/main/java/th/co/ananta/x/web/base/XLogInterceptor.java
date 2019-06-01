package th.co.ananta.x.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.web.unsecure.signin.SignInCtl;


public class XLogInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(XLogInterceptor.class);
	private static final String TIME_KEY = "TIME-KEY";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {

			HandlerMethod hm = (HandlerMethod) handler;

			if (hm.getBean() instanceof Secure) {

				HttpSession session = request.getSession(Constant.FALSE);
				if (session == null) {
					response.sendRedirect(request.getContextPath() + Constant.SLASH + SignInCtl.PATH);
					response.flushBuffer();
					return false;
				}

				Agent agent = (Agent) session.getAttribute(Constant.AGENT);
				if (agent == null) {
					response.sendRedirect(request.getContextPath() + Constant.SLASH + SignInCtl.PATH);
					response.flushBuffer();
					return false;
				}

			}

		}
		request.setAttribute(TIME_KEY, System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// log.info("XLogInterceptor.postHandle URL: " + request.getRequestURL());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		//log.info("URL: " + request.getRequestURL() + ":: elapsed time= " + (System.currentTimeMillis() - (Long) request.getAttribute(TIME_KEY)));
		if (ex != null) {
			ex.printStackTrace();
		}
	}

}
