package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.LoginHistory;
import th.co.ananta.x.core.repo.ILoginHistoryRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class LoginHistoryRepository implements ILoginHistoryRepository {

	private static final Logger log = LoggerFactory.getLogger(LoginHistoryRepository.class);

	@Override
	public int create(Agent agent) throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		int affected = 0;
		try {
			xSQL = new XSQL("INSERT INTO DTBL_LOG_LOGIN (EMAIL,BRANCH,CREATEDATE) VALUES(?, ?, ?)");
			xSQL.setString(1, agent.getEmail());
			xSQL.setString(2, agent.getBranchCode());
			xSQL.setObject(3, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(new Date().getTime()));
			affected = xSQL.executeUpdate();
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		return affected;
	}

	@Override
	public List<LoginHistory> list() throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		List<LoginHistory> objs = new ArrayList<LoginHistory>();

		try {
			xSQL = new XSQL("select BRANCH, EMAIL,CREATEDATE from DTBL_LOG_LOGIN ORDER BY CREATEDATE DESC");
			rs = xSQL.executeQuery();
			while (rs.next()) {
				LoginHistory obj = new LoginHistory();
				obj.setBranch(rs.getString("BRANCH"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setCreateDate(rs.getString("CREATEDATE"));
				objs.add(obj);
			}

		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}

		return objs;
	}

	@Override
	public List<LoginHistory> listByPage(int page, int number) throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		List<LoginHistory> objs = new ArrayList<LoginHistory>(8);
		try {
			int index = Math.multiplyExact(Integer.valueOf(page) - 1, 8);
			int lastRecord = Math.addExact(index, 8);
			int lastIndex = number >= index && number <= lastRecord ? number : lastRecord;
			log.info("index: " + index + ", lastRecord: " + lastRecord + ", lastIndex: " + lastIndex);
			int row = 0;
			xSQL = new XSQL("select BRANCH, EMAIL,CREATEDATE from DTBL_LOG_LOGIN ORDER BY CREATEDATE DESC");
			rs = xSQL.executeQuery();
			while (rs.next()) {
				if (row >= index && row < lastIndex) {
					log.info("row: " + row);
					LoginHistory obj = new LoginHistory();
					obj.setBranch(rs.getString("BRANCH"));
					obj.setEmail(rs.getString("EMAIL"));
					obj.setCreateDate(rs.getString("CREATEDATE"));
					objs.add(obj);
				}
				row = row + 1;
			}

		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}

		return objs;
	}

	@Override
	public int count() throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		int count = 0;
		try {
			xSQL = new XSQL("select COUNT(CREATEDATE) AS ROWCOUNT from DTBL_LOG_LOGIN");
			rs = xSQL.executeQuery();
			while (rs.next()) {
				count = rs.getInt("ROWCOUNT");
				log.info("count = " + count);
			}

		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}

		return count;
	}

}
