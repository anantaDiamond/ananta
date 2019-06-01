package th.co.ananta.x.web.secure.search;

import java.io.Serializable;

public class SalesForm implements Serializable{

	private String salesPerson;
	private String branchCode;
	private String proposedPrice;
	
	public String getSalesPerson() {
		return salesPerson;
	}
	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getProposedPrice() {
		return proposedPrice;
	}
	public void setProposedPrice(String proposedPrice) {
		this.proposedPrice = proposedPrice;
	}
	
}
