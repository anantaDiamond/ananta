package th.co.ananta.x.core.repo;

import java.math.BigDecimal;

import th.co.ananta.x.web.base.XException;

public interface IDiamondMarginPriceRepository {
	BigDecimal get(String shape, String size, String clarity, String color) throws XException;
}
