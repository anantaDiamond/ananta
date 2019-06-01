package th.co.ananta.x.core.repo;

import th.co.ananta.x.web.base.XException;
import th.co.ananta.x.core.domain.Agent;

public interface IAuthorizeRepository {
	int get(Agent agent) throws XException;
}
