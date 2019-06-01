package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.CaratSize;
import th.co.ananta.x.core.repo.ICaratSizeRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class CaratSizeRepository implements ICaratSizeRepository {
	private static final Logger log = LoggerFactory.getLogger(CaratSizeRepository.class);
	@Override
	public int update(CaratSize obj) throws XException {
		log.info("max="+obj.getMax()+", min="+obj.getMin());
		log.info("date ="+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(new Date().getTime()));
		XSQL xSQL = null;
		int affected = 0;
		try {
			xSQL = new XSQL("INSERT INTO DTBL_SIZE_MAX_MIN (MAXC,MINC,UPDDATE) VALUES(?,?,?)");
			xSQL.setString(1, obj.getMax());
			xSQL.setString(2, obj.getMin());
			xSQL.setObject(3, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(new Date().getTime()));
			affected = xSQL.executeUpdate();

		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
		return affected;
	}

	@Override
	public CaratSize get() throws XException {
		CaratSize obj = new CaratSize();
		int affected = 0;
		XSQL xSQL = null;
		ResultSet rs = null;
		try {
			xSQL = new XSQL("select MAXC, MINC from DTBL_SIZE_MAX_MIN ORDER BY UPDDATE DESC");
			rs = xSQL.executeQuery();
			if (rs.next()) {
				obj.setMax(rs.getString("MAXC"));
				obj.setMin(rs.getString("MINC"));
				log.info("min="+obj.getMin()+",max ="+obj.getMax());
				affected = 1;
			}

		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		return obj;
	}

}
