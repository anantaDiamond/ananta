package th.co.ananta.x.core.srv;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.core.repo.IDiamondInfoRepository;
import th.co.ananta.x.core.repo.IDiamondMarginPriceRepository;
import th.co.ananta.x.core.repo.IExchangeRateRepository;
import th.co.ananta.x.web.base.XException;

public class SearchDiamondInfoSrv {
	private static final Logger log = LoggerFactory.getLogger(SearchDiamondInfoSrv.class);
	private static int PERCENT = 100;
	IExchangeRateRepository ieRep;
	IDiamondMarginPriceRepository idmRep;
	IDiamondInfoRepository idRep;
	
	public List<Diamond> getDiamondsByCaratColorClarity(Agent agent, String maxCarat, String minCarat, String[] colors, String[] clarities) throws XException {
		List<Diamond> result = new ArrayList<Diamond>();
		List<Diamond> ds = idRep.getByCaratColorClarity(agent, maxCarat, minCarat, colors, clarities);		
		for (int i=0; i<(ds.size()<5?ds.size():5); i++) {
			Diamond d = ds.get(i);
			getPrice(d);
			result.add(d);
		}
		return result;
	}

	public BigDecimal getPrice(Diamond d) throws XException {
		log.info("price:"+d.getCost());
		BigDecimal rate = ieRep.get();
		BigDecimal thaiCurrency = null != rate ? rate.multiply(d.getCost()):d.getCost();
		d.setCost(thaiCurrency);
		BigDecimal margin = null != idmRep.get(d.getSize(), d.getShape(), d.getColor(), d.getClarity()) ? idmRep.get(d.getSize(), d.getShape(), d.getColor(), d.getClarity()) : new BigDecimal(0);
		BigDecimal marginAdd = (margin.multiply(thaiCurrency).divide(new BigDecimal(PERCENT))).add(thaiCurrency);
		int len = String.valueOf(Math.round(marginAdd.floatValue())).length();
		int roundPos = len-3;
		BigDecimal tenPowerPos = new BigDecimal(Math.pow(10, roundPos));
		BigDecimal round1 = marginAdd.divide(tenPowerPos);
		BigDecimal round2 = new BigDecimal(Math.ceil(round1.doubleValue()));
		BigDecimal newPrice = round2.multiply(tenPowerPos);
		d.setPrice(newPrice);
		log.info("price:"+d.getCost()+"rate:"+rate+"margin:"+margin+"thaiCurrency = "+thaiCurrency+"marginAdd = "+marginAdd+"newPrice = "+newPrice);
		return newPrice;
	}
	
	public void setIeRep(IExchangeRateRepository ieRep) {
		this.ieRep = ieRep;
	}


	public IDiamondMarginPriceRepository getIdmRep() {
		return idmRep;
	}

	public void setIdmRep(IDiamondMarginPriceRepository idmRep) {
		this.idmRep = idmRep;
	}

	public IDiamondInfoRepository getIdRep() {
		return idRep;
	}

	public void setIdRep(IDiamondInfoRepository idRep) {
		this.idRep = idRep;
	}

	public IExchangeRateRepository getIeRep() {
		return ieRep;
	}

	public static void main(String[] args) {
//		BigDecimal price = new BigDecimal(1793);
//		BigDecimal rate = new BigDecimal(32.5);
//		BigDecimal margin = new BigDecimal(10);
//		BigDecimal thaiCurrency = rate.multiply(price);
//		log.info("thaiCurrency = "+thaiCurrency);
//		BigDecimal marginAdd = (margin.multiply(thaiCurrency).divide(new BigDecimal(PERCENT))).add(thaiCurrency);
//		int len = String.valueOf(Math.round(marginAdd.floatValue())).length();
//		int roundPos = len-3;
//		BigDecimal tenPowerPos = new BigDecimal(Math.pow(10, roundPos));
//		BigDecimal round1 = marginAdd.divide(tenPowerPos);
//		BigDecimal round2 = new BigDecimal(Math.ceil(round1.doubleValue()));
//		BigDecimal newPrice = round2.multiply(tenPowerPos);
//		log.info("marginAdd = "+marginAdd);
//		log.info("newPrice = "+newPrice);
		int count=16;
		int x = Math.floorMod(count, 8);
		System.out.println(x);
	}
	
}
