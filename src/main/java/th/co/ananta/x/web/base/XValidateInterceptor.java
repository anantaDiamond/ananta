package th.co.ananta.x.web.base;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.web.unsecure.signin.SignInCtl;


public class XValidateInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(XValidateInterceptor.class);
	private static final String TIME_KEY = "TIME-KEY";
	private static final String TOKEN_KEY = "tokenKey";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("XValidateInterceptor.preHandle URL: " + request.getRequestURL() + ":: Read session token key :" + request.getSession().getAttribute(TOKEN_KEY));

		if (null != request.getSession()) {
			log.info("XValidateInterceptor.preHandle URL: " + request.getRequestURL() + ":: Method :" + request.getMethod()+":: Read request token key :" + request.getParameter(TOKEN_KEY));

				if (null != request.getMethod() && request.getMethod().equals("POST")) {
					String token = (String) request.getParameter(TOKEN_KEY);
					if (null != token && !StringUtils.isEmpty(token)) {
						if (null != request.getSession().getAttribute(TOKEN_KEY) && request.getSession().getAttribute(TOKEN_KEY).equals(token)) {
							String tokenKey = UUID.randomUUID().toString();
							request.getSession().setAttribute(TOKEN_KEY, tokenKey);
							log.info("XValidateInterceptor.preHandle URL: " + request.getRequestURL() + ":: Generated token key : " + tokenKey);
						} else {
							response.sendRedirect(request.getContextPath() + Constant.SLASH +  SignInCtl.PATH);
							response.flushBuffer();
							return false;
						}
					}
				}
		}
		Agent agent = (Agent) request.getSession(Constant.FALSE).getAttribute(Constant.AGENT);
		if (handler instanceof HandlerMethod) {

			HandlerMethod hm = (HandlerMethod) handler;

			if (hm.getBean() instanceof Secure) {

				HttpSession session = request.getSession(Constant.FALSE);

				if (session == null) {
					response.sendRedirect(request.getContextPath() + Constant.SLASH + SignInCtl.PATH);
					response.flushBuffer();
					return true;
				}

				if (agent == null) {
					response.sendRedirect(request.getContextPath() + Constant.SLASH + SignInCtl.PATH);
					response.flushBuffer();
					return true;
				}

				log.info(agent.toString());

			}
		} else if (handler.getClass().getName().equals("org.springframework.webflow.mvc.servlet.FlowHandlerMapping$DefaultFlowHandler")) {

			HttpSession session = request.getSession(Constant.FALSE);

			if (session == null) {
				response.sendRedirect(request.getContextPath() + Constant.SLASH + SignInCtl.PATH);
				response.flushBuffer();
				return false;
			}


			if (agent == null) {
				response.sendRedirect(request.getContextPath() + Constant.SLASH + SignInCtl.PATH);
				response.flushBuffer();
				return false;
			}
			log.info(agent.toString());
		}

		request.setAttribute(TIME_KEY, System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		 log.info("XValidateInterceptor.postHandle URL: " + request.getRequestURL());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("XValidateInterceptor.afterCompletion URL: " + request.getRequestURL() + ":: elapsed time= " + (System.currentTimeMillis() - (Long) request.getAttribute(TIME_KEY)));
	}

}
