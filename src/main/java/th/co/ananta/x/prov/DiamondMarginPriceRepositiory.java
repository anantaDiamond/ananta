package th.co.ananta.x.prov;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.repo.IDiamondMarginPriceRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class DiamondMarginPriceRepositiory implements IDiamondMarginPriceRepository{
	private static final Logger log = LoggerFactory.getLogger(DiamondMarginPriceRepositiory.class);
	@Override
	public BigDecimal get(String size, String shape, String color, String clarity) throws XException {
		log.info("shape:"+shape+",color:"+color+",size:"+size+",clarity:"+clarity);
		BigDecimal margin = null;
		XSQL xSQL = null;
		ResultSet rs = null;
		int index=1;
		try {
			xSQL = new XSQL("SELECT MARGIN FROM STBL_MARGIN WHERE SHAPE=? AND COLOR = ? AND CLARITY=? AND ? BETWEEN SUBSTRING_INDEX(SIZE, '-', 1) and SUBSTRING_INDEX(SIZE, '-', -1)");	
			xSQL.setString(index++, shape);
			xSQL.setString(index++, color);
			xSQL.setString(index++, clarity);
			xSQL.setObject(index++, size);
			
			rs = xSQL.executeQuery();
			while (rs.next()) {
				margin = !StringUtils.isBlank(rs.getString("MARGIN"))?new BigDecimal(rs.getString("MARGIN")):null;
				log.info("x:"+margin);
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		return margin;
	}
}
