package th.co.ananta.x.core.repo;

import th.co.ananta.x.core.domain.CaratSize;
import th.co.ananta.x.web.base.XException;

public interface ICaratSizeRepository {
	
	int update(CaratSize obj) throws XException;
	CaratSize get() throws XException;
		
}
