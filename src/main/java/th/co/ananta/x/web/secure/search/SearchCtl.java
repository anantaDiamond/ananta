package th.co.ananta.x.web.secure.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.CaratSize;
import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.core.domain.Order;
import th.co.ananta.x.core.repo.ICaratSizeRepository;
import th.co.ananta.x.core.repo.ICustomerOrderRepository;
import th.co.ananta.x.core.srv.GenerateOrderIdSrv;
import th.co.ananta.x.core.srv.SearchDiamondInfoSrv;
import th.co.ananta.x.prov.CaratSizeRepository;
import th.co.ananta.x.prov.CustomerOrderRepository;
import th.co.ananta.x.prov.DiamondInfoRepository;
import th.co.ananta.x.prov.DiamondMarginPriceRepositiory;
import th.co.ananta.x.prov.ExchangeRateRepository;
import th.co.ananta.x.prov.OrderIdRepository;
import th.co.ananta.x.web.base.Constant;
import th.co.ananta.x.web.base.GenericValidator;
import th.co.ananta.x.web.base.Secure;
import th.co.ananta.x.web.base.XException;

@Controller
public class SearchCtl extends Secure {

	private static final Logger log = LoggerFactory.getLogger(SearchCtl.class);
	public static final String PATH = "/search";
	public static final String REDIRECT = "redirect:" + PATH;
	
	public void initializeForm(RequestContext rc, MessageContext mc, SearchForm form) {
		log.info("searchCtl.initializeForm");
		form = new SearchForm();
		form.setColor(new String[] {});
		form.setClarity(new String[] {});
		System.out.println("xxx"+form.getMaxCarat());
		System.out.println("xxx"+form.getMinCarat());
	}
	
	public String search(RequestContext rc, MessageContext mc, SearchForm form) throws XException {
		log.info("searchCtl.search");
		
		Agent agent = (Agent) rc.getExternalContext().getSessionMap().get(Constant.AGENT);
		log.info(agent.getName());
		log.info(agent.getEmail());
		SearchDiamondInfoSrv searchSrv = new SearchDiamondInfoSrv();
		searchSrv.setIeRep(new ExchangeRateRepository());
		searchSrv.setIdRep(new DiamondInfoRepository());
		searchSrv.setIdmRep(new DiamondMarginPriceRepositiory());
		List<Diamond> result = null;
		try {
			result = searchSrv.getDiamondsByCaratColorClarity(agent, form.getMaxCarat(), form.getMinCarat(), form.getColor(), form.getClarity());
		} catch (XException e) {
			throw e;
		}
			//form.setDiamonds(new String[] {});
		form.setDiamonds(new ArrayList<String>());
		rc.getFlowScope().put("result", result);
		return Constant.SUCCESS;
	}
	
	public String confirm(RequestContext rc, MessageContext mc, SearchForm form) {
		log.info("searchCtl.confirm");
		Agent agent = (Agent) rc.getExternalContext().getSessionMap().get(Constant.AGENT);
		form.setCustomer(new CustomerForm());
		form.setSales(new SalesForm());
		
		return Constant.SUCCESS;
	}
	
	public String submit(RequestContext rc, MessageContext mc, SearchForm form) throws XException {
		log.info("searchCtl.submit");
		Agent agent = (Agent) rc.getExternalContext().getSessionMap().get(Constant.AGENT);
		
		List<Diamond> results = null;
		if (null != rc.getFlowScope().get("result")) {
			results = (List<Diamond>) rc.getFlowScope().get("result");
		}

		GenerateOrderIdSrv goSrv = new GenerateOrderIdSrv();
		goSrv.setIoRep(new OrderIdRepository()); 			
		CustomerOrder order = new CustomerOrder();
		order.setOrderId(goSrv.generateId(agent));
		log.info("orderID = "+order.getOrderId());
		if (null != form.getDiamonds() & form.getDiamonds().size()>0) {
			order.setOrders(new ArrayList<Order>());
			for (int i=0; i<form.getDiamonds().size(); i++) {
				Diamond d = new Diamond();
				Order od = new Order();
				d.setId(form.getDiamonds().get(i));		
				for (Diamond dm : results) {
					if (dm.getId().equals(d.getId())) {
						d.setClarity(dm.getClarity());
						d.setColor(dm.getColor());
						d.setShape(dm.getShape());
						d.setSize(dm.getSize());
						d.setSupplier(dm.getSupplier());
						d.setLotLocation(dm.getLotLocation());
						d.setKeyToSymbols(dm.getKeyToSymbols());
						d.setCost(dm.getCost());
						od.setPrice(dm.getPrice());
					}
				}
				
				od.setDiamond(d);
				od.setStatus(Constant.ORDER.STATUS_NOT_START);
				order.getOrders().add(od);
				order.setStatus(Constant.ORDER.STATUS_NOT_START);
				order.setCustomerName(form.getCustomer().getName());
				order.setEmail(form.getCustomer().getEmail());
				order.setPhone(form.getCustomer().getPhone());
				order.setSalesName(form.getSales().getSalesPerson());
				order.setBranch(form.getSales().getBranchCode());
				order.setProposed(form.getSales().getProposedPrice());
				order.setQuotationNumber(form.getQuotationNumber());
				order.setDateNeeded(form.getDateNeed());
			}
		}
		
		ICustomerOrderRepository iorder = new CustomerOrderRepository();
		try {
			iorder.create(order);
		} catch (XException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		log.info("orderId = "+order.getOrderId());
		rc.getFlashScope().put("orderId", order.getOrderId());
		return Constant.SUCCESS;
	}
	
	public String delete(RequestContext rc, MessageContext mc, SearchForm form) throws XException {
		log.info("searchCtl.delete");
		
		String index = rc.getRequestParameters().get("index");
		if (!StringUtils.isBlank(index)) {
			log.info("index = "+index);
			if (form.getDiamonds().size()>1) {
				form.getDiamonds().remove(Integer.parseInt(index));	
			} else {
				MessageBuilder mb = new MessageBuilder().error();
				mb.code("error.search.delete.not.allow");			
				mc.addMessage(mb.build());
				return Constant.FAIL;
			}
		}
		return Constant.SUCCESS;
	}
	
	public Event validateSearchCondition(RequestContext rc, MessageContext mc, SearchForm form) throws XException {
		if (StringUtils.isBlank(form.getMinCarat()) && StringUtils.isBlank(form.getMaxCarat()) && (null == form.getClarity() || form.getClarity().length==0) && (null==form.getColor() || form.getColor().length==0) ) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.code("error.search.required.condition");
			mc.addMessage(mb.build());
		}		
		
		if (!StringUtils.isBlank(form.getMinCarat())) {
			ICaratSizeRepository carat = new CaratSizeRepository();
			CaratSize obj =  carat.get();
			if (null != obj && !StringUtils.isBlank(obj.getMin()) && Double.parseDouble(obj.getMin())>Double.parseDouble(form.getMinCarat())) {
				MessageBuilder mb = new MessageBuilder().error();
				mb.source("minCarat");
				mb.code("error.search.min.exceed");
				mb.arg(obj.getMin());
				mc.addMessage(mb.build());
			}
		}		
		if (!StringUtils.isBlank(form.getMaxCarat())) {
			ICaratSizeRepository carat = new CaratSizeRepository();
			CaratSize obj =  carat.get();
			if (null != obj && !StringUtils.isBlank(obj.getMax()) && Double.parseDouble(obj.getMax())<Double.parseDouble(form.getMaxCarat())) {
				MessageBuilder mb = new MessageBuilder().error();
				mb.source("maxCarat");
				mb.code("error.search.max.exceed");
				mb.arg(obj.getMax());
				mc.addMessage(mb.build());
			}
		}		
		if (mc.getAllMessages().length > 0) {
			return new EventFactorySupport().error(this);
		}

		return new EventFactorySupport().success(this);
	}
	
	public Event validateSearchResult(RequestContext rc, MessageContext mc, SearchForm form) {
		if (null == form.getDiamonds() || form.getDiamonds().size()==0) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.code("error.account.required.diamond.selected");
			mc.addMessage(mb.build());
		}
		if (mc.getAllMessages().length > 0) {
			return new EventFactorySupport().error(this);
		}

		
		return new EventFactorySupport().success(this);
	}

	public Event validateConfirmForm(RequestContext rc, MessageContext mc, SearchForm form) {
		if (StringUtils.isEmpty(form.getCustomer().getName())) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.source("customer.name");
			mb.code("error.search.required.customer.name");
			mc.addMessage(mb.build());
		}
		if (StringUtils.isEmpty(form.getCustomer().getEmail())) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.source("customer.email");
			mb.code("error.search.required.customer.email");
			mc.addMessage(mb.build());
		} else {
			if (!GenericValidator.isValidEmailAddress(form.getCustomer().getEmail())) {
				MessageBuilder mb = new MessageBuilder().error();
				mb.source("customer.email");
				mb.code("error.search.customer.email.invalid");
				mc.addMessage(mb.build());
			}
		}
		if (StringUtils.isEmpty(form.getCustomer().getPhone())) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.source("customer.phone");
			mb.code("error.search.required.customer.phone");
			mc.addMessage(mb.build());
		}
		if (StringUtils.isEmpty(form.getSales().getSalesPerson())) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.source("sales.salesPerson");
			mb.code("error.search.required.sales.salesPerson");
			mc.addMessage(mb.build());
		}
		if (StringUtils.isEmpty(form.getSales().getBranchCode())) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.source("sales.branchCode");
			mb.code("error.search.required.sales.branchCode");
			mc.addMessage(mb.build());
		}
		if (null == form.getDateNeed()) {
			MessageBuilder mb = new MessageBuilder().error();
			mb.source("dateNeed");
			mb.code("error.search.required.dateNeed");
			mc.addMessage(mb.build());
		}
		
		if (mc.getAllMessages().length > 0) {
			return new EventFactorySupport().error(this);
		}
		
		return new EventFactorySupport().success(this);
	}

	public void exception(MessageContext messageContext, XException e) {
		messageContext.addMessage(new MessageBuilder().error().code(e.getCode()).defaultText(e.getMessage()).build());
	}
}
