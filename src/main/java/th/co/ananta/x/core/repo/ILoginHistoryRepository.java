package th.co.ananta.x.core.repo;

import java.util.List;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.LoginHistory;
import th.co.ananta.x.web.base.XException;

public interface ILoginHistoryRepository {

	
	int create(Agent agent) throws XException;
	List<LoginHistory> list() throws XException;
	List<LoginHistory> listByPage(int page, int number) throws XException;
	public int count() throws XException;
}
