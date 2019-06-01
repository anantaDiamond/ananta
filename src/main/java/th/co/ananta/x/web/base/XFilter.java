package th.co.ananta.x.web.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import th.co.ananta.x.core.domain.Agent;

@Component
@Order(1)
public class XFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("Content-Security-Policy", "frame-ancestors 'self'");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		// response.addHeader("Strict-Transport-Security", "max-age=7776000; cludeSubdomains");
		// response.addHeader("Cache-control", "no-cache, no-store,max-age=0, must-revalidate");
		// response.addHeader("Pragma", "no-cache");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		// Show Session ID in Log
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);
		try {
			if (null != session) {
				MDC.put("sessionId", session.getId());
				Agent agent = (Agent) request.getSession(false).getAttribute(Constant.AGENT);
				if (null != agent) {
					MDC.put("agentId", agent.getEmail());
				}
			}
			chain.doFilter(req, res);
		} finally {
			MDC.clear();
		}

	}

	@Override
	public void destroy() {
	}

}
