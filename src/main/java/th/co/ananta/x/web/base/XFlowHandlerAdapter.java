package th.co.ananta.x.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;

public class XFlowHandlerAdapter extends FlowHandlerAdapter {

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding(Constant.ENCODING);
		response.setCharacterEncoding(Constant.ENCODING);
		return super.handle(request, response, handler);
	}

}