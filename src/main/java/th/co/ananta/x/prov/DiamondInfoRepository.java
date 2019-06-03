package th.co.ananta.x.prov;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.Agent;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.core.repo.IDiamondInfoRepository;
import th.co.ananta.x.prov.host.MSSQL;
import th.co.ananta.x.web.base.XException;

public class DiamondInfoRepository implements IDiamondInfoRepository {
	private static final Logger log = LoggerFactory.getLogger(DiamondInfoRepository.class);
	@Override
	public List<Diamond> getByCaratColorClarity(Agent agent, String maxCarat, String minCarat, String[] colors, String[] clarities) throws XException {
		log.info("getByCaratColorClarity: max carat="+maxCarat+",min carat="+minCarat);
		// TODO Auto-generated method stub
		MSSQL xSQL = null;
		ResultSet rs = null;
		List<Diamond> list = new ArrayList<Diamond>();
		int index = 1;

		try {
			List<String> whereClauseList = new ArrayList<String>();
//			StringBuffer query = new StringBuffer("SELECT DIAMONDID, SHAPE, COLOR, CLARITY, CERTNUM, SIZE, TOTALSALESPRICE, SUPPLIER, LOTLOCATION, UPDATED, date(Updated) AS UPDATEDATE) FROM DTBL_DIAMOND WHERE ");
			StringBuffer query = new StringBuffer("SELECT TOP 5 DIAMONDID, SHAPE, COLOR, CLARITY, CERTNUM, SIZE, TOTALSALESPRICE, SUPPLIER, LOTLOCATION, KEYTOSYMBOLS, UPDATED FROM diamond WHERE ");
			if (!StringUtils.isBlank(minCarat)) {
				query.append("SIZE >= ? AND ");
				whereClauseList.add(minCarat);
			}
			if (!StringUtils.isBlank(maxCarat)) {
				query.append("SIZE <= ? AND ");
				whereClauseList.add(maxCarat);
			}
			if (null != colors && colors.length>0) {
				query.append("(");
				for (String color : colors) {
					query.append("COLOR = ? OR ");
					whereClauseList.add(color);
				}
				query = new StringBuffer(query.substring(0, query.length() - 4));
				query.append(") AND ");
			}
			if (null != clarities && clarities.length>0) {
				query.append("(");
				for (String clarity : clarities) {
					query.append("CLARITY = ? OR ");
					whereClauseList.add(clarity);
				}		
				query = new StringBuffer(query.substring(0, query.length() - 4));			
				query.append(") AND ");
			}
			query = new StringBuffer(query.substring(0, query.length() - 5));
//			query.append("ORDER BY UPDATEDATE DESC, TOTALSALESPRICE ASC LIMIT 5");
			query.append("ORDER BY UPDATE DESC, TOTALSALESPRICE ASC");
			xSQL = new MSSQL(query.toString());
			for (int i = 0; i < whereClauseList.size(); ++i) {
				xSQL.setString(i + 1, whereClauseList.get(i));
			}
			rs = xSQL.executeQuery();
			while (rs.next()) {
				Diamond diamond = new Diamond();
				diamond.setId(String.valueOf(rs.getInt("DIAMONDID")));
				diamond.setShape(rs.getString("SHAPE"));
				diamond.setColor(rs.getString("COLOR"));
				diamond.setClarity(rs.getString("CLARITY"));
				diamond.setGiaCert(rs.getString("CERTNUM"));
				diamond.setSize(null != rs.getBigDecimal("SIZE") ? String.valueOf(rs.getBigDecimal("SIZE")):null);
				diamond.setUpdatedTime(rs.getString("UPDATED"));
				diamond.setCost(rs.getBigDecimal("TOTALSALESPRICE"));
				diamond.setSupplier(rs.getString("SUPPLIER"));
				diamond.setLotLocation(rs.getString("LOTLOCATION"));
				diamond.setKeyToSymbols(rs.getString("KEYTOSYMBOLS"));
				//diamond.setPhone(rs.getString("PHONE"));
				list.add(diamond);
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			rs = null;
			xSQL.close();
		}
		return list;
	}

}
