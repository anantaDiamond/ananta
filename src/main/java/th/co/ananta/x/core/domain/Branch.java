package th.co.ananta.x.core.domain;

import java.io.Serializable;

public class Branch implements Serializable {

	private String code;
	private String description;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
