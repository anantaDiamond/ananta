package th.co.ananta.x.web.tag.form;

public class PasswordInputTag extends org.springframework.web.servlet.tags.form.PasswordInputTag {

	private static final String OFF = "off";

	public PasswordInputTag() {
		setAutocomplete(OFF);
		setCssClass("form-control");
		setCssErrorClass("form-control is-invalid");
	}

}
