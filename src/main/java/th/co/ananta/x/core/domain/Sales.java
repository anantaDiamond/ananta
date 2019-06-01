package th.co.ananta.x.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Sales implements Serializable {
	
	private String salesName;
	private String branch;
	private String proposed;
	private String quotationNumber;
	private Date dateNeeded;
	
	
	public String getSalesName() {
		return salesName;
	}
	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getProposed() {
		return proposed;
	}
	public void setProposed(String proposed) {
		this.proposed = proposed;
	}
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	public Date getDateNeeded() {
		return dateNeeded;
	}
	public void setDateNeeded(Date dateNeeded) {
		this.dateNeeded = dateNeeded;
	}
	
	
	
}
