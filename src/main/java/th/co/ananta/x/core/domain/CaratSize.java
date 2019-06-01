package th.co.ananta.x.core.domain;

import java.io.Serializable;

public class CaratSize implements Serializable{

	private String min;
	private String max;
	
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
}
