package th.co.ananta.x.core.repo;

import java.util.List;

import th.co.ananta.x.core.domain.BranchEmail;
import th.co.ananta.x.web.base.XException;

public interface IBranchEmailRepository {

	void create(BranchEmail object) throws XException;
	BranchEmail list() throws XException;
	List<String> get(String branch) throws XException;
}
