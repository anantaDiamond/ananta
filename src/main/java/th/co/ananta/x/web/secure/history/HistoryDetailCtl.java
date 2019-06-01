package th.co.ananta.x.web.secure.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.core.repo.ICustomerOrderRepository;
import th.co.ananta.x.prov.CustomerOrderRepository;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;

@Controller
public class HistoryDetailCtl extends Secure{
	private static final Logger log = LoggerFactory.getLogger(HistoryDetailCtl.class);

	public static final String PATH = "/history/detail";
	public static final String VIEW_NAME = "site.history.detail";
	public static final String REDIRECT = "redirect:" + PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request) throws XException {
		log.info("history.detail");
		String orderId = request.getParameter("orderId");
		ICustomerOrderRepository cus = new CustomerOrderRepository();
		try {
			Map<String, List<CustomerOrder>> cusOrder = cus.list();
			if (!StringUtils.isBlank(orderId)) {
				for (String id : cusOrder.keySet()) {
					if (orderId.equals(id)) {
						List<CustomerOrder> orders = new ArrayList<CustomerOrder>();
						orders = cusOrder.get(id);	
						request.setAttribute("orders", orders);
					}
				}
			}
			
		} catch (XException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		return mav;
	}

}
