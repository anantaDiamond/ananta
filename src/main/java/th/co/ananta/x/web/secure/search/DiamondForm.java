package th.co.ananta.x.web.secure.search;

import java.io.Serializable;
import java.math.BigDecimal;

public class DiamondForm implements Serializable{

	private String clarity;
	private String shape;
	private String comment;
	private String color;
	private BigDecimal price;
	private String giaCert;
	private String size;
	private String id;
	
	
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getGiaCert() {
		return giaCert;
	}
	public void setGiaCert(String giaCert) {
		this.giaCert = giaCert;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
