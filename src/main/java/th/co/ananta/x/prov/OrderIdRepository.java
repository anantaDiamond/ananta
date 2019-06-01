package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.repo.IOrderIdRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class OrderIdRepository implements IOrderIdRepository {
	private static final Logger log = LoggerFactory.getLogger(OrderIdRepository.class);
	@Override
	public int create(String id, String branch, String createDate) throws XException {
		int affected = 0;
		XSQL xSQL = null;
		try {
			xSQL = new XSQL("INSERT INTO DTBL_ORDER_ID (BRANCH,CREATEDATE,ID) VALUES(?,?,?)");
			xSQL.setString(1, branch);
			xSQL.setString(2, createDate);
			xSQL.setString(3, id);
			xSQL.executeUpdate();
			affected = 1;
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
		
		return affected;
	}

	
	@Override
	public int update(String id, String branch, String createDate) throws XException {
		int affected = 0;
		XSQL xSQL = null;
		try {
			xSQL = new XSQL("UPDATE DTBL_ORDER_ID SET ID=? WHERE BRANCH=? AND CREATEDATE=?");
			xSQL.setString(1, id);
			xSQL.setString(2, branch);
			xSQL.setString(3, createDate);
			xSQL.executeUpdate();
			affected = 1;
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
		
		return affected;
	}

	@Override
	public String get(String branch, String createDate) throws XException {
		String id=null;
		XSQL xSQL = null;
		ResultSet rs = null;
		try {
			xSQL = new XSQL("select ID from DTBL_ORDER_ID where BRANCH=? and CREATEDATE=?");
			xSQL.setString(1, branch);
			xSQL.setString(2, createDate);
			rs = xSQL.executeQuery();
			if (rs.next()) {
				id = rs.getString("ID");
			}
		
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		
		return id;
	}

}
