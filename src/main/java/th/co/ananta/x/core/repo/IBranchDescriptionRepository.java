package th.co.ananta.x.core.repo;

import java.util.List;

import th.co.ananta.x.core.domain.Branch;
import th.co.ananta.x.web.base.XException;

public interface IBranchDescriptionRepository {

	List<Branch> list() throws XException;
	
}
