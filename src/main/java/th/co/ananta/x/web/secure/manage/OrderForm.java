package th.co.ananta.x.web.secure.manage;

import java.io.Serializable;

public class OrderForm implements Serializable{

	private String status;
	private String diamondId;
	private String id;
	private String note;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDiamondId() {
		return diamondId;
	}
	public void setDiamondId(String diamondId) {
		this.diamondId = diamondId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}	
	
}
