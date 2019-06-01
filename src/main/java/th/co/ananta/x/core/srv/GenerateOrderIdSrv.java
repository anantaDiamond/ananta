package th.co.ananta.x.core.srv;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.web.base.XException;
import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.core.repo.IOrderIdRepository;
import th.co.ananta.x.prov.OrderIdRepository;

public class GenerateOrderIdSrv {
	
	private static final Logger log = LoggerFactory.getLogger(GenerateOrderIdSrv.class);
	IOrderIdRepository ioRep;
	
	public String generateId(Agent agent) throws XException {
		String createDate = new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date());
		String id = ioRep.get(agent.getBranchCode(), createDate);
		if (!StringUtils.isBlank(id)) {
			int newId = Integer.parseInt(id)+1;
			ioRep.update(String.valueOf(newId), agent.getBranchCode(), createDate);
		} else {
			ioRep.create("1", agent.getBranchCode(), createDate);
		}
		
		String newOrderId = agent.getBranchCode()+createDate+StringUtils.leftPad(ioRep.get(agent.getBranchCode(), createDate).toString(),2,"0");
		log.info("id = "+newOrderId);
		return newOrderId;
	}

	public void setIoRep(IOrderIdRepository ioRep) {
		this.ioRep = ioRep;
	}
	
}
