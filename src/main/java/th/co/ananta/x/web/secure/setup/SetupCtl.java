package th.co.ananta.x.web.secure.setup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.BranchEmail;
import th.co.ananta.x.core.domain.CaratSize;
import th.co.ananta.x.core.domain.ExchangeRate;
import th.co.ananta.x.core.srv.SetupSrv;
import th.co.ananta.x.prov.BranchEmailRepository;
import th.co.ananta.x.prov.CaratSizeRepository;
import th.co.ananta.x.prov.ExchangeRateRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;

@Controller
public class SetupCtl extends Secure {
	
	private static final Logger log = LoggerFactory.getLogger(SetupCtl.class);
	public static final String PATH = "/setup";
	public static final String VIEW_NAME = "site.setup";
	public static final String REDIRECT_PATH = "/setupResponse";
	public static final String REDIRECT = "redirect:" + REDIRECT_PATH;
	public static final String REDIRECT_ERROR = "redirect:" + PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request, @ModelAttribute(Constant.FORM) SetupForm frm, BindingResult br) throws XException {
		log.info("setup");
				
		ModelAndView mav = new ModelAndView(VIEW_NAME);		
		frm.setEmail(new String[] {});		
		mav.addObject(Constant.FORM, frm);	
		
		return mav;
	}
	
	@PostMapping(value = PATH)
	public ModelAndView execute(HttpServletRequest request, @Valid @ModelAttribute(Constant.FORM) SetupForm frm, BindingResult br) throws XException {
		log.info("setup.update");
		
		CaratSize carat = new CaratSize();
		carat.setMax(frm.getMaxCarat());
		carat.setMin(frm.getMinCarat());
		
		ExchangeRate rate = new ExchangeRate();
		rate.setRate(!StringUtils.isBlank(frm.getRate()) ? new BigDecimal(frm.getRate()) : null);
				
		if (null != frm.getEmail() && frm.getEmail().length>0 && StringUtils.isBlank(frm.getBranch())) {
			ModelAndView mav = new ModelAndView(REDIRECT_ERROR);
			mav.addObject(Constant.FORM, frm);
			br.addError(new ObjectError(Constant.FORM, super.getMessage("errors.setup.required.branch")));
			return mav;			
		}
		
		BranchEmail email = new BranchEmail();
		Map<String, List<String>> bemail = new LinkedHashMap<String, List<String>>();
		List<String> mails = new ArrayList<String>();
		if (null != frm.getEmail() && frm.getEmail().length>0) {
			for (int i=0; i<frm.getEmail().length; i++) {
				mails.add(frm.getEmail()[i]);
			}
			bemail.put(frm.getBranch(), mails);
			email.setBranchEmail(bemail);
		}
		
		log.info("min:"+carat.getMin()+", max:"+carat.getMax()+", rate:"+rate.getRate());
		SetupSrv setup = new SetupSrv();
		setup.setbRepo(new BranchEmailRepository());
		setup.setcRepo(new CaratSizeRepository());
		setup.seteRepo(new ExchangeRateRepository());
		setup.update(carat, rate, email);
		
		ModelAndView mav = new ModelAndView(REDIRECT);
		return mav;
		
	}

}
