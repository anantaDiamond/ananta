package th.co.ananta.x.web.unsecure.signin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.srv.AuthenSrv;
import th.co.ananta.x.prov.AuthorizationRepository;
import th.co.ananta.x.prov.LoginHistoryRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.Unsecure;
import th.co.ananta.x.web.base.XException;
import th.co.ananta.x.web.secure.homepage.HomepageCtl;

@Controller
public class SignInCtl extends Unsecure{

	private static final Logger log = LoggerFactory.getLogger(SignInCtl.class);

	public static final String PATH = "/signin";
	private static final String VIEW_NAME = "site.signin";

	@GetMapping(value = { "/", PATH })
	public ModelAndView form(HttpServletRequest request, @ModelAttribute(Constant.FORM) SignInForm frm, BindingResult br) {
		log.info("Signin");
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(Constant.FORM, frm);
		return mav;
	}

	@PostMapping(value = PATH)
	public ModelAndView execute(HttpServletRequest request, @Valid @ModelAttribute(Constant.FORM) SignInForm frm, BindingResult br) {
		try {
			if (br.hasErrors()) {
				ModelAndView mav = new ModelAndView(VIEW_NAME);
				mav.addObject(Constant.FORM, frm);
				return mav;
			} else {
				log.info("[User : "+frm.getName()+", Email :"+frm.getEmail()+"]");
				Agent agent = new Agent();
				agent.setName(frm.getName());
				agent.setEmail(frm.getEmail());
				AuthenSrv aSrv = new AuthenSrv();
				aSrv.setAuthRep(new AuthorizationRepository());
				aSrv.setLoginRep(new LoginHistoryRepository());
				if (aSrv.authorize(agent)) {
					HttpSession session = request.getSession(Constant.TRUE);
					session.setMaxInactiveInterval(Constant.MAX_INACTIVE_INTERVAL);
					session.setAttribute(Constant.AGENT, agent);
					ModelAndView mav = new ModelAndView(HomepageCtl.REDIRECT);
					return mav;
				}
				throw new XException(new Exception(getMessage("error.signin.failed")));
			}
		} catch (XException e) {
			ModelAndView mav = new ModelAndView(VIEW_NAME);
			mav.addObject(Constant.FORM, frm);
			br.addError(new ObjectError(Constant.FORM, e.getMessage()));
			return mav;
		}
	}
}
