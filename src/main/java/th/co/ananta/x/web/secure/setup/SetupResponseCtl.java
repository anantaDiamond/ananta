package th.co.ananta.x.web.secure.setup;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.BranchEmail;
import th.co.ananta.x.core.domain.CaratSize;
import th.co.ananta.x.core.domain.ExchangeRate;
import th.co.ananta.x.core.repo.IBranchEmailRepository;
import th.co.ananta.x.core.repo.ICaratSizeRepository;
import th.co.ananta.x.core.repo.IExchangeRateRepository;
import th.co.ananta.x.prov.BranchEmailRepository;
import th.co.ananta.x.prov.CaratSizeRepository;
import th.co.ananta.x.prov.ExchangeRateRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;

@Controller
public class SetupResponseCtl extends Secure{

	private static final Logger log = LoggerFactory.getLogger(SetupCtl.class);
	public static final String PATH = "/setupResponse";
	public static final String VIEW_NAME = "site.setup.response";
	public static final String REDIRECT = "redirect:" + PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request, @ModelAttribute(Constant.FORM) SetupForm frm, BindingResult br) throws XException {
		log.info("setup");
				
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		CaratSize carat = new CaratSize();
		ExchangeRate rate = new ExchangeRate();
		BranchEmail email = new BranchEmail();
		ICaratSizeRepository cRepo = new CaratSizeRepository();
		IExchangeRateRepository eRepo = new ExchangeRateRepository();
		IBranchEmailRepository bRepo = new BranchEmailRepository();
		log.info("min="+carat.getMin()+",max ="+carat.getMax());
		carat = cRepo.get();
		rate.setRate(eRepo.get());
		email = bRepo.list();
		log.info("min="+carat.getMin()+",max ="+carat.getMax());
		request.setAttribute("carat", carat);
		request.setAttribute("rate", rate);
		request.setAttribute("email", email);
		
		mav.addObject(Constant.FORM, frm);	
		
		return mav;
	}
	
}
