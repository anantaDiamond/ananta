package th.co.ananta.x.web.secure.history;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.core.repo.ICustomerOrderRepository;
import th.co.ananta.x.prov.CustomerOrderRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;
import th.co.ananta.x.web.secure.manage.ManageForm;

@Controller
public class HistoryCtl extends Secure{

	private static final Logger log = LoggerFactory.getLogger(HistoryCtl.class);

	public static final String PATH = "/history";
	public static final String VIEW_NAME = "site.history.list";
	public static final String REDIRECT = "redirect:" + PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request, @ModelAttribute(Constant.FORM) ManageForm frm, BindingResult br) throws XException {
		log.info("history");
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		String page = request.getParameter("page");
		String status = request.getParameter("status");
		ICustomerOrderRepository cus = new CustomerOrderRepository();
		Map<String, List<CustomerOrder>> x = cus.list();
		int count = null != x ? x.size():0;
		log.info("page :"+count);
		if (StringUtils.isBlank(page)) {
			page ="1";
		}
		
		try {
			Map<String, List<CustomerOrder>> cusOrder = null;
			if (!StringUtils.isBlank(status) && !status.equals(Constant.ORDER.STATUS_ALL)) {
				frm.setStatusFilter(status);
				cusOrder = cus.listByStatus(Integer.valueOf(page), count, status);
			} else {
				if (!StringUtils.isBlank(status)) {
					frm.setStatusFilter(status);
				}
				cusOrder = cus.listByPage(Integer.valueOf(page), count);
			}
			request.setAttribute("cusOrder", cusOrder);
		} catch (XException e) {
			// TODO Auto-generated catch block
			mav.addObject(Constant.FORM, frm);
			br.addError(new ObjectError(Constant.FORM, e.getMessage()));
			return mav;
		}
				
		int pageNumber = 0 != Math.floorMod(count, 8) ? Math.addExact(Math.floorDiv(count, 8),1):Math.floorDiv(count, 8);
		request.setAttribute("page", pageNumber);
		return mav;
	}
	
}
