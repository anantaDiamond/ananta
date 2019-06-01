package th.co.ananta.x.core.repo;

import java.math.BigDecimal;

import th.co.ananta.x.web.base.XException;

public interface IExchangeRateRepository {

	BigDecimal get() throws XException;
	int create(BigDecimal rate) throws XException;
	
}
