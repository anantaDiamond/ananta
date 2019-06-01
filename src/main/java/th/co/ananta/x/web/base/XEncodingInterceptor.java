package th.co.ananta.x.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class XEncodingInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding(Constant.ENCODING);
		response.setCharacterEncoding(Constant.ENCODING);
		return super.preHandle(request, response, handler);
	}

}
