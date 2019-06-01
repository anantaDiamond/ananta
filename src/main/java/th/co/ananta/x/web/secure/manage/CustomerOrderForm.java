package th.co.ananta.x.web.secure.manage;

import java.io.Serializable;
import java.util.List;

public class CustomerOrderForm implements Serializable {

	private List<OrderForm> orders;
	private String status;
	private String id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<OrderForm> getOrders() {
		return orders;
	}
	public void setOrders(List<OrderForm> orders) {
		this.orders = orders;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
