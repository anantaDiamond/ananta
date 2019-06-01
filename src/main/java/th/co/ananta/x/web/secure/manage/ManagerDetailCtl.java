package th.co.ananta.x.web.secure.manage;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.core.domain.Order;
import th.co.ananta.x.core.repo.ICustomerOrderRepository;
import th.co.ananta.x.prov.CustomerOrderRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;

@Controller
public class ManagerDetailCtl extends Secure{
	private static final Logger log = LoggerFactory.getLogger(ManagerDetailCtl.class);
	public static final String PATH = "/manage/detail";
	public static final String REDIRECT_PATH = "/manage";
	public static final String VIEW_NAME = "site.manage.detail";
	public static final String REDIRECT = "redirect:" + REDIRECT_PATH;

	@GetMapping(value = PATH )
	public ModelAndView form(HttpServletRequest request, @ModelAttribute(Constant.FORM) ManageForm frm, BindingResult br) throws XException {
		log.info("history.detail");
		ModelAndView mav = new ModelAndView(VIEW_NAME);
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
						String xx = null;
						CustomerOrderForm cfrm = new CustomerOrderForm();
						List<OrderForm> ofrms = new ArrayList<OrderForm>();
						for (CustomerOrder order : orders) {
							for (Order o : order.getOrders()) {
								OrderForm ofrm = new OrderForm();
								ofrm.setStatus(o.getStatus());
								ofrm.setNote(o.getNote());
								log.info("dId="+o.getDiamond().getId()+", status="+o.getStatus()+", note="+o.getNote());
								ofrm.setDiamondId(o.getDiamond().getId());
								
								ofrms.add(ofrm);
							}
							xx = order.getOrderId();
							cfrm.setStatus(order.getStatus());
						}				
						cfrm.setId(xx);
						cfrm.setOrders(ofrms);
						frm.setCusOrder(cfrm);	
					}						
				}			
				mav.addObject(Constant.FORM, frm);
			}
				
		} catch (XException e) {
			// TODO Auto-generated catch block
			throw e;
		}	
				
		return mav;
	}
	
	@PostMapping(value = PATH)
	public ModelAndView execute(HttpServletRequest request, @Valid @ModelAttribute(Constant.FORM) ManageForm frm, BindingResult br) throws XException {
		log.info("manage.update");
		
		ICustomerOrderRepository ic = new CustomerOrderRepository();
		
		CustomerOrder co = new CustomerOrder();
		List<Order> os = new ArrayList<Order>();
		
		CustomerOrderForm cfrm = frm.getCusOrder();
		List<OrderForm> ofrms = cfrm.getOrders();
		Map<String, String> status = new LinkedHashMap<String, String>();
		for (OrderForm ofrm : ofrms) {
			Order o = new Order();
			o.setStatus(ofrm.getStatus());
			Diamond d = new Diamond();
			d.setId(ofrm.getDiamondId());
			d.setNote(ofrm.getNote());
			o.setDiamond(d);
			os.add(o);
			status.put(o.getStatus(), d.getId());
		}
		co.setOrderId(cfrm.getId());
		if (status.size()>0 && (null != status.get(Constant.ORDER.STATUS_NOT_START) && null == status.get(Constant.ORDER.STATUS_IN_PROCESS) && null == status.get(Constant.ORDER.STATUS_SUCCESS) && null == status.get(Constant.ORDER.STATUS_CANCEL))) {
			co.setStatus(Constant.ORDER.STATUS_NOT_START);
		} else if (status.size()>0 && (null != status.get(Constant.ORDER.STATUS_NOT_START) || null != status.get(Constant.ORDER.STATUS_IN_PROCESS))) {
			co.setStatus(Constant.ORDER.STATUS_IN_PROCESS);
		} else if (status.size()>0 && (null == status.get(Constant.ORDER.STATUS_NOT_START) && null == status.get(Constant.ORDER.STATUS_IN_PROCESS))) {
			co.setStatus(Constant.ORDER.STATUS_DONE);
		} 
		
		co.setOrders(os);
		
		ic.update(co);
		
		ModelAndView mav = new ModelAndView(REDIRECT);
		return mav;
		
	}
	

}
