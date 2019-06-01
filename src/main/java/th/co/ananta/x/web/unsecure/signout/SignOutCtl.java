package th.co.ananta.x.web.unsecure.signout;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.web.base.Unsecure;

@Controller
public class SignOutCtl extends Unsecure {
	private static final Logger log = LoggerFactory.getLogger(SignOutCtl.class);

	public static final String PATH = "/signout";
	private static final String REDIRECT = "redirect:/signin";

	@GetMapping(value = PATH)
	public ModelAndView form(HttpServletRequest request) {
		log.info("form");
		request.getSession().invalidate();
		ModelAndView mav = new ModelAndView(REDIRECT);
		return mav;
	}
}
