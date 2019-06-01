package th.co.ananta.x.core.repo;

import java.util.List;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.web.base.XException;

public interface IDiamondInfoRepository {
	
	List<Diamond> getByCaratColorClarity(Agent agent, String maxCarat, String minCarat, String[] colors,
			String[] clarities) throws XException ;
	
	
}
