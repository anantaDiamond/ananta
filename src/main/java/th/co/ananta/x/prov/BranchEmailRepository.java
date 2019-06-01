package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.BranchEmail;
import th.co.ananta.x.core.repo.IBranchEmailRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class BranchEmailRepository implements IBranchEmailRepository {

	private static final Logger log = LoggerFactory.getLogger(BranchEmailRepository.class);
	public static final String ROLE_SALES = "S";
	
	@Override
	public void create(BranchEmail object) throws XException {
		XSQL xSQL = null;
		int affected = 0;
		try {
			for (String branch : object.getBranchEmail().keySet()) {
				List<String> emails = object.getBranchEmail().get(branch);
				for (String email : emails) {
					xSQL = new XSQL("INSERT INTO DTBL_USER_LOGIN (BRCD,EMAIL,ROL,CREATEDATE) VALUES(?,?,?,?)");
					xSQL.setString(1, branch);
					xSQL.setString(2, email);
					xSQL.setString(3, ROLE_SALES);
					xSQL.setObject(4, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(new Date().getTime()));				
					affected = xSQL.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
	}

	@Override
	public BranchEmail list() throws XException {		
		XSQL xSQL = null;
		ResultSet rs = null;
		BranchEmail ob = new BranchEmail();
		Map<String, List<String>> branchEmails = new LinkedHashMap<String, List<String>>();
		List<String> emails = new ArrayList<String>();
		try {
			xSQL = new XSQL("select BRCD, EMAIL from DTBL_USER_LOGIN WHERE ROL = ?");
			xSQL.setString(1, ROLE_SALES);
			rs = xSQL.executeQuery();
			while (rs.next()) {
				if (null == branchEmails.get(rs.getString("BRCD"))) {
					emails = new ArrayList<String>();
					emails.add(rs.getString("EMAIL"));
					branchEmails.put(rs.getString("BRCD"), emails);
				} else {
					emails.add(rs.getString("EMAIL"));
					branchEmails.put(rs.getString("BRCD"), emails);
				}				
			}
			ob.setBranchEmail(branchEmails);
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}

		return ob;
	}

	@Override
	public List<String> get(String branch) throws XException {
		XSQL xSQL = null;
		ResultSet rs = null;
		List<String> emails = new ArrayList<String>();
		try {
			xSQL = new XSQL("select EMAIL from DTBL_USER_LOGIN WHERE BRCD=? AND ROL = ?");
			xSQL.setString(1, branch);
			xSQL.setString(2, ROLE_SALES);
			rs = xSQL.executeQuery();			
			while (rs.next()) {
				emails.add(rs.getString("EMAIL"));						
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
		return emails;
	}
	
}
