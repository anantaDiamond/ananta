package th.co.ananta.x.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

import th.co.ananta.x.web.base.Constant;

public class Diamond implements Serializable{

	private String id;
	private String clarity;
	private String shape;
	private String comment;
	private String color;
	private BigDecimal price;
	private BigDecimal cost;
	private String giaCert;
	private String size;
	private String supplier;
	private String lotLocation;
	private String phone;
	@DateTimeFormat(pattern = Constant.FORMAT_DATE)
	private String updatedTime;
	private String note;
	
	public String getClarity() {
		return clarity;
	}
	public void setClarity(String clarity) {
		this.clarity = clarity;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGiaCert() {
		return giaCert;
	}
	public void setGiaCert(String giaCert) {
		this.giaCert = giaCert;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getLotLocation() {
		return lotLocation;
	}
	public void setLotLocation(String lotLocation) {
		this.lotLocation = lotLocation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
