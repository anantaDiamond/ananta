package th.co.ananta.x.web.tag.form;

public class ButtonTag extends org.springframework.web.servlet.tags.form.ButtonTag {

	private String BUTTON_CSS_CLASS = "btn";

	public ButtonTag() {
		setCssClass(BUTTON_CSS_CLASS);
	}
}
