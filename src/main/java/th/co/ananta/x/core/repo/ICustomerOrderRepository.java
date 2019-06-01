package th.co.ananta.x.core.repo;

import java.util.List;
import java.util.Map;

import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.web.base.XException;

public interface ICustomerOrderRepository {
	
	int create(CustomerOrder obj) throws XException;
	Map<String, List<CustomerOrder>> list() throws XException;
	Map<String, List<CustomerOrder>> listByPage(int page, int number) throws XException;
	Map<String, List<CustomerOrder>> listByStatus(int page, int number, String status) throws XException;
	CustomerOrder get() throws XException;
	int update(CustomerOrder obj) throws XException;
	
}
