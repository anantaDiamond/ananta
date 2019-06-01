package th.co.ananta.x.prov.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.core.repo.IDiamondInfoRepository;
import th.co.ananta.x.web.base.XException;

public class DiamondInfoRepository implements IDiamondInfoRepository{

	@Override
	public List<Diamond> getByCaratColorClarity(Agent agent, String maxCarat, String minCarat, String[] colors,
			String[] clarities) throws XException {
		
		List<Diamond> ds = new ArrayList<Diamond>();
		Diamond d = new Diamond();
		d.setClarity("VVS1");
		d.setColor("D");
		d.setGiaCert("GIAS");
		d.setId("11111111");
		d.setPrice(new BigDecimal(59513));
		d.setShape("Round");
		d.setSize("3.12");
		d.setUpdatedTime("22:19");
		ds.add(d);
		d = new Diamond();
		d.setClarity("VVS2");
		d.setColor("F");
		d.setGiaCert("GIAS");
		d.setId("11111112");
		d.setPrice(new BigDecimal(59513));
		d.setShape("Round");
		d.setSize("16");
		d.setUpdatedTime("22:19");
		ds.add(d);
		
		
		return ds;
	}

}
