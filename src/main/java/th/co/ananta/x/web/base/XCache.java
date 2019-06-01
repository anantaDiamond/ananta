package th.co.ananta.x.web.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import th.co.ananta.x.core.domain.Branch;
import th.co.ananta.x.core.repo.IBranchDescriptionRepository;
import th.co.ananta.x.prov.BranchDescriptionRepository;

public class XCache implements Serializable{

		
	static {
		try {
			new XCache();
		} catch (XException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> statusDescription;
	public static Map<String, String> orderStatusDescription;
	public static Map<String, String> branchDescription;
	public static Map<String, String> emailTemplate;
	
	public XCache() throws XException {
		reload();
	}

	public static void reload() throws XException {
		statusDescription = statusDescription();
		branchDescription = branchDescription();
		orderStatusDescription = orderStatusDescription();
		emailTemplate = emailTemplate();
	}
	
	private static Map<String, String> statusDescription() {
		Map<String, String> statusDescription = new TreeMap<String, String>();
		statusDescription.put("N", "Not Started");
		statusDescription.put("I", "In Process");
		statusDescription.put("S", "Success");
		statusDescription.put("C", "Cancelled");
		return statusDescription;
	}
	

	public static Map<String, String> getStatusDescription() {
		return statusDescription;
	}
	
	private static Map<String, String> orderStatusDescription() {
		Map<String, String> orderStatusDescription = new TreeMap<String, String>();
		orderStatusDescription.put("A", "All");
		orderStatusDescription.put("D", "Done");
		orderStatusDescription.put("I", "In Process");
		orderStatusDescription.put("N", "Not Started");
		return orderStatusDescription;
	}
	
	public static Map<String, String> getOrderStatusDescription() {
		return orderStatusDescription;
	}

	private static Map<String, String> branchDescription() throws XException {
		Map<String, String> branchDescription = new TreeMap<String, String>();
		IBranchDescriptionRepository bRep = new BranchDescriptionRepository();
		List<Branch> objs =  bRep.list();
		for (Branch obj: objs) {
			branchDescription.put(obj.getCode(), obj.getDescription());
		}
		return branchDescription;
	}

	public static Map<String, String> getBranchDescription() {
		return branchDescription;
	}
	
	private static Map<String, String> emailTemplate() {
		Map<String, String> emailTemplate = new TreeMap<String, String>();
		emailTemplate.put("01", "Report for Buyer");
		return orderStatusDescription;
	}

	public static Map<String, String> getEmailTemplate() {
		return emailTemplate;
	}

	
}
