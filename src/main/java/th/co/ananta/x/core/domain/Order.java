package th.co.ananta.x.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable{

	private Diamond diamond;
	private BigDecimal price;
	private String status;
	private String note;
	
	public Diamond getDiamond() {
		return diamond;
	}
	public void setDiamond(Diamond diamond) {
		this.diamond = diamond;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
