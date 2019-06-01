package th.co.ananta.x.web.secure.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.LoginHistory;
import th.co.ananta.x.core.repo.ILoginHistoryRepository;
import th.co.ananta.x.prov.LoginHistoryRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;

@Controller
public class LoginHistoryCtl extends Secure{
	private static final Logger log = LoggerFactory.getLogger(LoginHistoryCtl.class);

	public static final String PATH = "/log";
	public static final String VIEW_NAME = "site.log.list";
	public static final String REDIRECT = "redirect:" + PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request) throws XException {
		log.info("log.login.history");
		
		String page = request.getParameter("page");
		ILoginHistoryRepository iLogin = new LoginHistoryRepository();
		int count = iLogin.count();
		log.info("page count = "+iLogin.count());
		
		if (StringUtils.isBlank(page)) {
			page ="1";
		}
		
		try {
			List<LoginHistory> obj = iLogin.listByPage(Integer.valueOf(page), count);
			request.setAttribute(Constant.OBJECT, obj);
		} catch (XException e) {
			throw e;
		}
		int pageNumber = 0 != Math.floorMod(count, 8) ? Math.addExact(Math.floorDiv(count, 8),1):Math.floorDiv(count, 8);
		request.setAttribute("page", pageNumber);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		return mav;
	}
}
