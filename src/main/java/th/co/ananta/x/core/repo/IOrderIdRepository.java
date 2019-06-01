package th.co.ananta.x.core.repo;

import th.co.ananta.x.web.base.XException;

public interface IOrderIdRepository {
	
	int create(String id, String branch, String createDate) throws XException;
	int update(String id, String branch, String createDate) throws XException;
	String get(String branch, String createDate) throws XException;
}
