package th.co.ananta.x.web.tag.form;

public class SelectTag extends org.springframework.web.servlet.tags.form.SelectTag {

	public SelectTag() {
		super();
		setCssClass("custom-select d-block w-100");
		setCssErrorClass("custom-select d-block w-100 is-invalid");
	}

}
