package th.co.ananta.x.web.secure.homepage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.web.base.Secure;

@Controller
public class HomepageCtl extends Secure{
	
	private static final Logger log = LoggerFactory.getLogger(HomepageCtl.class);

	public static final String PATH = "/homepage";
	public static final String VIEW_NAME = "site.homepage";
	public static final String REDIRECT = "redirect:" + PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request) {
		log.info("Homepage");
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		return mav;
	}

}
