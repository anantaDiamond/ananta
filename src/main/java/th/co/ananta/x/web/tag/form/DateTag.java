package th.co.ananta.x.web.tag.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;

public class DateTag extends org.springframework.web.servlet.tags.form.InputTag {

	private static final String OFF = "off";

	private static final String ATTRIBUTE_DATA_PROVIDE = "data-provide";
	private static final String ATTRIBUTE_DATA_DATE_LANGUAGE = "data-date-language";

	public DateTag() {
		setAutocomplete(OFF);
		setCssClass("form-control");
		setCssErrorClass("form-control is-invalid");
	}

	@Override
	protected void writeOptionalAttributes(TagWriter tagWriter) throws JspException {
		writeOptionalAttribute(tagWriter, ATTRIBUTE_DATA_PROVIDE, "datepicker");
		writeOptionalAttribute(tagWriter, ATTRIBUTE_DATA_DATE_LANGUAGE, "th-th");
		super.writeOptionalAttributes(tagWriter);
	}
}
