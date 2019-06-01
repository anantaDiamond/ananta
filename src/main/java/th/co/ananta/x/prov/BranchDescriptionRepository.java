package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import th.co.ananta.x.core.domain.Branch;
import th.co.ananta.x.core.repo.IBranchDescriptionRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class BranchDescriptionRepository implements IBranchDescriptionRepository{

	@Override
	public List<Branch> list() throws XException {
		// TODO Auto-generated method stub
		XSQL xSQL = null;
		ResultSet rs = null;
		List<Branch> obj = new ArrayList<Branch>();
		try {
			xSQL = new XSQL("SELECT BRCD,DES FROM STBL_BRANCH");
			rs = xSQL.executeQuery();
			while (rs.next()) {
				Branch b = new Branch();
				b.setCode(rs.getString("BRCD"));
				b.setDescription(rs.getString("DES"));
				obj.add(b);
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
