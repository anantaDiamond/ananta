package th.co.ananta.x.prov;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.repo.IExchangeRateRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class ExchangeRateRepository implements IExchangeRateRepository{
	private static final Logger log = LoggerFactory.getLogger(ExchangeRateRepository.class);
	
	@Override
	public BigDecimal get() throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		BigDecimal rate = null;
		try {
			xSQL = new XSQL("SELECT RATE FROM DTBL_EXCHANGE_RATE ORDER BY UPDDATE DESC");
			rs = xSQL.executeQuery();
			if (rs.next()) {
				rate = rs.getBigDecimal("RATE");
				log.info("rate:"+rate);
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		return rate;
	}
	
	@Override
	public int create(BigDecimal rate) throws XException {
		// TODO Auto-generated method stub
		XSQL xSQL = null;
		ResultSet rs = null;
		int affected = 0;
		try {
			xSQL = new XSQL("INSERT INTO DTBL_EXCHANGE_RATE (RATE,UPDDATE) VALUES(?, ?)");
			xSQL.setString(1, null != rate ? rate.toString() : null);
			xSQL.setObject(2, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(new Date().getTime()));
			affected = xSQL.executeUpdate();			
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		return affected;
	}

}
