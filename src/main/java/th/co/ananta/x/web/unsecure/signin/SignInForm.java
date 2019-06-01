package th.co.ananta.x.web.unsecure.signin;

import java.io.Serializable;

public class SignInForm implements Serializable{

	private String name;
	
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
}
