package th.co.ananta.x.web.secure.search;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import th.co.ananta.x.web.base.Constant;

public class SearchForm implements Serializable{

	private String minCarat;
	private String maxCarat;
	private String[] color;
	private String[] clarity;
	private List<String> diamonds;
	private CustomerForm customer;
	private SalesForm sales;
	private String quotationNumber;
	@DateTimeFormat(pattern = Constant.FORMAT_DATE)
	private Date dateNeed;
	private String orderId;
	
	public String getMinCarat() {
		return minCarat;
	}
	public void setMinCarat(String minCarat) {
		this.minCarat = minCarat;
	}
	public String getMaxCarat() {
		return maxCarat;
	}
	public void setMaxCarat(String maxCarat) {
		this.maxCarat = maxCarat;
	}
	public String[] getColor() {
		return color;
	}
	public void setColor(String[] color) {
		this.color = color;
	}
	public String[] getClarity() {
		return clarity;
	}
	public void setClarity(String[] clarity) {
		this.clarity = clarity;
	}
	public CustomerForm getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerForm customer) {
		this.customer = customer;
	}
	public SalesForm getSales() {
		return sales;
	}
	public void setSales(SalesForm sales) {
		this.sales = sales;
	}
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getDateNeed() {
		return dateNeed;
	}
	public void setDateNeed(Date dateNeed) {
		this.dateNeed = dateNeed;
	}
	public List<String> getDiamonds() {
		return diamonds;
	}
	public void setDiamonds(List<String> diamonds) {
		this.diamonds = diamonds;
	}
	
}
