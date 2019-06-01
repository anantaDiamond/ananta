package th.co.ananta.x.core.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BranchEmail implements Serializable  {
	
	
	private Map<String, List<String>> branchEmail;

	public Map<String, List<String>> getBranchEmail() {
		return branchEmail;
	}

	public void setBranchEmail(Map<String, List<String>> branchEmail) {
		this.branchEmail = branchEmail;
	}

}
