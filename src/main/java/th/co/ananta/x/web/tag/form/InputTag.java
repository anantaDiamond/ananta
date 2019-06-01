package th.co.ananta.x.web.tag.form;

public class InputTag extends org.springframework.web.servlet.tags.form.InputTag {

	private static final String OFF = "off";

	public InputTag() {
		setAutocomplete(OFF);
		setCssClass("form-control");
		setCssErrorClass("form-control is-invalid");
	}
}
