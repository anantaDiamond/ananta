package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.repo.IAuthorizeRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class AuthorizationRepository implements IAuthorizeRepository {
	private static final Logger log = LoggerFactory.getLogger(AuthorizationRepository.class);
	@Override
	public int get(Agent agent) throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		int affected = 0;
		int index=1;
		try {
			xSQL = new XSQL("SELECT ROL, BRCD FROM DTBL_USER_LOGIN WHERE EMAIL = ?");
			xSQL.setString(index++, agent.getEmail());
			rs = xSQL.executeQuery();
			while (rs.next()) {
				agent.setRole(rs.getString("ROL"));
				agent.setBranchCode(rs.getString("BRCD"));
				log.info("agent: role="+agent.getRole()+", branch="+agent.getBranchCode());
				affected = 1;
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		
		return affected;
	}

}
