package th.co.ananta.x.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExchangeRate implements Serializable  {

	private BigDecimal rate;

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}	
	
}
