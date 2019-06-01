package th.co.ananta.x.web.secure.setup;

import java.io.Serializable;

public class SetupForm implements Serializable{
	
	private String maxCarat;
	private String minCarat;
	private String rate;
	private String branch;
	private String[] email;
	
	public String getMaxCarat() {
		return maxCarat;
	}
	public void setMaxCarat(String maxCarat) {
		this.maxCarat = maxCarat;
	}
	public String getMinCarat() {
		return minCarat;
	}
	public void setMinCarat(String minCarat) {
		this.minCarat = minCarat;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String[] getEmail() {
		return email;
	}
	public void setEmail(String[] email) {
		this.email = email;
	}


}
