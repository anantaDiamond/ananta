package th.co.ananta.x.web.secure.manage;

import java.io.Serializable;

public class ManageForm implements Serializable{

	private CustomerOrderForm cusOrder;
	private String statusFilter;

	public CustomerOrderForm getCusOrder() {
		return cusOrder;
	}

	public void setCusOrder(CustomerOrderForm cusOrder) {
		this.cusOrder = cusOrder;
	}

	public String getStatusFilter() {
		return statusFilter;
	}

	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;
	}
	
}
