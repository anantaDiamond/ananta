package th.co.ananta.x.prov;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import th.co.ananta.x.core.domain.CustomerOrder;
import th.co.ananta.x.core.domain.Diamond;
import th.co.ananta.x.core.domain.Order;
import th.co.ananta.x.core.repo.ICustomerOrderRepository;
import th.co.ananta.x.prov.host.XSQL;
import th.co.ananta.x.web.base.XException;

public class CustomerOrderRepository implements ICustomerOrderRepository {
	private static final Logger log = LoggerFactory.getLogger(CustomerOrderRepository.class);

	@Override
	public int create(CustomerOrder obj) throws XException {
		// TODO Auto-generated method stub
		int affected = 0;
		XSQL xSQL = null;
		int index = 1;
		try {
			String dateTime = "" + new Date().getTime();

			for (Order order : obj.getOrders()) {
				xSQL = new XSQL(
						"INSERT INTO DTBL_CUS_ORDER (ORDERID, CREATEDATE, DIAMONDID, CLARITY, SHAPE, SIZ, COLOR, COMMENTS, PRICE, ORDSTATUS, CUSNAME, EMAIL, PHONE, SALESNAME, BRANCH, PROPOSED, QUANUM, DATENEED, STATUS, SUPPLIER, SUPPHONE, SUPLOC, COST) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)");
				xSQL.setString(1, obj.getOrderId());
				xSQL.setString(2, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US).format(new Date().getTime()));
				xSQL.setString(3, order.getDiamond().getId());
				xSQL.setString(4, order.getDiamond().getClarity());
				xSQL.setString(5, order.getDiamond().getShape());
				xSQL.setString(6, order.getDiamond().getSize());
				xSQL.setString(7, order.getDiamond().getColor());
				xSQL.setString(8, order.getDiamond().getComment());
				xSQL.setString(9, null != order.getPrice() ? order.getPrice().toString() : null);
				xSQL.setString(10, order.getStatus());
				xSQL.setString(11, obj.getCustomerName());
				xSQL.setString(12, obj.getEmail());
				xSQL.setString(13, obj.getPhone());
				xSQL.setString(14, obj.getSalesName());
				xSQL.setString(15, obj.getBranch());
				xSQL.setString(16, obj.getProposed());
				xSQL.setString(17, obj.getQuotationNumber());
				xSQL.setString(18,
						null != obj.getDateNeeded()
								? new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(obj.getDateNeeded())
								: null);
				xSQL.setString(19, obj.getStatus());
				xSQL.setString(20, order.getDiamond().getSupplier());
				xSQL.setString(21, order.getDiamond().getPhone());
				xSQL.setString(22, order.getDiamond().getLotLocation());
				xSQL.setString(23,
						null != order.getDiamond().getCost() ? order.getDiamond().getCost().toString() : null);
				log.info(obj.getBranch());
				affected = xSQL.executeUpdate();
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}

		return affected;
	}

	@Override
	public Map<String, List<CustomerOrder>> list() throws XException {
		// TODO Auto-generated method stub
		log.info("customer order list");
		Map<String, List<CustomerOrder>> maporder = new TreeMap<String, List<CustomerOrder>>();

		List<CustomerOrder> list = null;
		XSQL xSQL = null;
		ResultSet rs = null;
		try {
			xSQL = new XSQL(
					"SELECT ORDERID, CREATEDATE, DIAMONDID, CLARITY, SHAPE, SIZ, COLOR, COMMENTS, PRICE, ORDSTATUS, CUSNAME, EMAIL, PHONE, SALESNAME, BRANCH, PROPOSED, QUANUM, DATENEED, STATUS, SUPPLIER, SUPPHONE, SUPLOC, COST, NOTE FROM DTBL_CUS_ORDER");
			rs = xSQL.executeQuery();
			while (rs.next()) {

				Map<String, CustomerOrder> mco = null;
				CustomerOrder co = new CustomerOrder();
				co.setCustomerName(rs.getString("CUSNAME"));
				co.setEmail(rs.getString("EMAIL"));
				co.setPhone(rs.getString("PHONE"));
				co.setSalesName(rs.getString("SALESNAME"));
				co.setBranch(rs.getString("BRANCH"));
				co.setProposed(rs.getString("PROPOSED"));
				co.setOrderId(rs.getString("ORDERID"));
				co.setStatus(rs.getString("STATUS"));
				co.setCreateDate(rs.getString("CREATEDATE"));
				Order order = new Order();
				Diamond d = new Diamond();
				d.setClarity(rs.getString("CLARITY"));
				d.setColor(rs.getString("COLOR"));
				d.setComment(rs.getString("COMMENTS"));
				d.setSize(rs.getString("SIZ"));
				d.setShape(rs.getString("SHAPE"));
				d.setSupplier(rs.getString("SUPPLIER"));
				d.setPhone(rs.getString("SUPPHONE"));
				d.setLotLocation(rs.getString("SUPLOC"));
				d.setCost(new BigDecimal(rs.getString("COST")));
				d.setId(rs.getString("DIAMONDID"));
				order.setDiamond(d);
				order.setPrice(rs.getBigDecimal("PRICE"));
				order.setStatus(rs.getString("ORDSTATUS"));
				order.setNote(rs.getString("NOTE"));
				List<Order> oo = new ArrayList<Order>();
				oo.add(order);
				co.setOrders(oo);
				if (null == maporder.get(rs.getString("ORDERID"))) {
					list = new ArrayList<CustomerOrder>();
				} 
				list.add(co);
				maporder.put(rs.getString("ORDERID"), list);
			}

		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}

		return maporder;
	}
	
	@Override
	public Map<String, List<CustomerOrder>> listByPage(int page, int number) throws XException {
		// TODO Auto-generated method stub
		log.info("customer.order.list.by.date");
		Map<String, List<CustomerOrder>> maporder = new LinkedHashMap<String, List<CustomerOrder>>();
		Map<String, List<CustomerOrder>> tmpmap = new TreeMap<String, List<CustomerOrder>>();
		
		List<CustomerOrder> list = null;
		XSQL xSQL = null;
		ResultSet rs = null;
		int index = Math.multiplyExact(Integer.valueOf(page) - 1, 8);
		int lastRecord = Math.addExact(index, 8);
		int lastIndex = number >= index && number <= lastRecord ? number : lastRecord;
		log.info("index: " + index + ", lastRecord: " + lastRecord + ", lastIndex: " + lastIndex);
		int recordCount = 0;
		
		try {
			xSQL = new XSQL(
					"SELECT ORDERID, CREATEDATE, DIAMONDID, CLARITY, SHAPE, SIZ, COLOR, COMMENTS, PRICE, ORDSTATUS, CUSNAME, EMAIL, PHONE, SALESNAME, BRANCH, PROPOSED, QUANUM, DATENEED, STATUS, SUPPLIER, SUPPHONE, SUPLOC, COST, NOTE FROM DTBL_CUS_ORDER ORDER BY CREATEDATE DESC");
			rs = xSQL.executeQuery();
			while (rs.next()) {
				
					Map<String, CustomerOrder> mco = null;
					CustomerOrder co = new CustomerOrder();
					co.setCustomerName(rs.getString("CUSNAME"));
					co.setEmail(rs.getString("EMAIL"));
					co.setPhone(rs.getString("PHONE"));
					co.setSalesName(rs.getString("SALESNAME"));
					co.setBranch(rs.getString("BRANCH"));
					co.setProposed(rs.getString("PROPOSED"));
					co.setOrderId(rs.getString("ORDERID"));
					co.setStatus(rs.getString("STATUS"));
					co.setCreateDate(rs.getString("CREATEDATE"));
					Order order = new Order();
					Diamond d = new Diamond();
					d.setClarity(rs.getString("CLARITY"));
					d.setColor(rs.getString("COLOR"));
					d.setComment(rs.getString("COMMENTS"));
					d.setSize(rs.getString("SIZ"));
					d.setShape(rs.getString("SHAPE"));
					d.setSupplier(rs.getString("SUPPLIER"));
					d.setPhone(rs.getString("SUPPHONE"));
					d.setLotLocation(rs.getString("SUPLOC"));
					d.setCost(new BigDecimal(rs.getString("COST")));
					d.setId(rs.getString("DIAMONDID"));
					order.setDiamond(d);
					order.setPrice(rs.getBigDecimal("PRICE"));
					order.setStatus(rs.getString("ORDSTATUS"));
					order.setNote(rs.getString("NOTE"));
					List<Order> oo = new ArrayList<Order>();
					oo.add(order);
					co.setOrders(oo);
					if (null == tmpmap.get(rs.getString("ORDERID"))) {
						list = new ArrayList<CustomerOrder>();
						recordCount=recordCount+1;
					} 
					list.add(co);
					tmpmap.put(rs.getString("ORDERID"), list);
					if (recordCount >= index && recordCount <= lastIndex) {
						log.info("row: " + recordCount);
						maporder.put(rs.getString("ORDERID"), list);
					}					
				}		
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}

		return maporder;
	}

	@Override
	public Map<String, List<CustomerOrder>> listByStatus(int page, int number, String status) throws XException {
		// TODO Auto-generated method stub
		log.info("customer.order.list.by.status");
		Map<String, List<CustomerOrder>> maporder = new LinkedHashMap<String, List<CustomerOrder>>();
		Map<String, List<CustomerOrder>> tmpmap = new TreeMap<String, List<CustomerOrder>>();
		
		List<CustomerOrder> list = null;
		XSQL xSQL = null;
		ResultSet rs = null;
		int index = Math.multiplyExact(Integer.valueOf(page) - 1, 8);
		int lastRecord = Math.addExact(index, 8);
		int lastIndex = number >= index && number <= lastRecord ? number : lastRecord;
		log.info("index: " + index + ", lastRecord: " + lastRecord + ", lastIndex: " + lastIndex);
		int recordCount = 0;
		
		try {
			xSQL = new XSQL(
					"SELECT ORDERID, CREATEDATE, DIAMONDID, CLARITY, SHAPE, SIZ, COLOR, COMMENTS, PRICE, ORDSTATUS, CUSNAME, EMAIL, PHONE, SALESNAME, BRANCH, PROPOSED, QUANUM, DATENEED, STATUS, SUPPLIER, SUPPHONE, SUPLOC, COST, NOTE FROM DTBL_CUS_ORDER WHERE STATUS=? ORDER BY CREATEDATE DESC");
			xSQL.setString(1, status);
			rs = xSQL.executeQuery();
			while (rs.next()) {
					Map<String, CustomerOrder> mco = null;
					CustomerOrder co = new CustomerOrder();
					co.setCustomerName(rs.getString("CUSNAME"));
					co.setEmail(rs.getString("EMAIL"));
					co.setPhone(rs.getString("PHONE"));
					co.setSalesName(rs.getString("SALESNAME"));
					co.setBranch(rs.getString("BRANCH"));
					co.setProposed(rs.getString("PROPOSED"));
					co.setOrderId(rs.getString("ORDERID"));
					co.setStatus(rs.getString("STATUS"));
					co.setCreateDate(rs.getString("CREATEDATE"));
					Order order = new Order();
					Diamond d = new Diamond();
					d.setClarity(rs.getString("CLARITY"));
					d.setColor(rs.getString("COLOR"));
					d.setComment(rs.getString("COMMENTS"));
					d.setSize(rs.getString("SIZ"));
					d.setShape(rs.getString("SHAPE"));
					d.setSupplier(rs.getString("SUPPLIER"));
					d.setPhone(rs.getString("SUPPHONE"));
					d.setLotLocation(rs.getString("SUPLOC"));
					d.setCost(new BigDecimal(rs.getString("COST")));
					d.setId(rs.getString("DIAMONDID"));
					order.setDiamond(d);
					order.setPrice(rs.getBigDecimal("PRICE"));
					order.setStatus(rs.getString("ORDSTATUS"));
					order.setNote(rs.getString("NOTE"));
					List<Order> oo = new ArrayList<Order>();
					oo.add(order);
					co.setOrders(oo);
					if (null == tmpmap.get(rs.getString("ORDERID"))) {
						list = new ArrayList<CustomerOrder>();
						recordCount=recordCount+1;
					} 
					list.add(co);
					tmpmap.put(rs.getString("ORDERID"), list);
					if (recordCount >= index && recordCount <= lastIndex) {
						log.info("row: " + recordCount);
						maporder.put(rs.getString("ORDERID"), list);
					}
				}			
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
		return maporder;
	}
	
	@Override
	public CustomerOrder get() throws XException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(CustomerOrder obj) throws XException {
		// TODO Auto-generated method stub
		int affected = 0;
		XSQL xSQL = null;
		int index = 1;
		try {
			for (Order order : obj.getOrders()) {
				xSQL = new XSQL("UPDATE DTBL_CUS_ORDER SET ORDSTATUS=?, STATUS=?, NOTE=? WHERE ORDERID=? AND DIAMONDID=?");
				
				xSQL.setString(1, order.getStatus());
				log.info(order.getStatus());
				xSQL.setString(2, obj.getStatus());
				log.info(obj.getStatus());
				xSQL.setString(3, order.getDiamond().getNote());
				xSQL.setString(4, obj.getOrderId());
				log.info(obj.getOrderId());
				xSQL.setString(5, order.getDiamond().getId());
				log.info(order.getDiamond().getId());
				affected = xSQL.executeUpdate();
			}
		} catch (SQLException e) {
			throw new XException(e);
		} finally {
			xSQL.close();
		}
		return affected;
	}

}
