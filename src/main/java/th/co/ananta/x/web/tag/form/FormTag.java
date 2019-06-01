package th.co.ananta.x.web.tag.form;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;

import th.co.ananta.x.web.base.Constant;

public class FormTag extends org.springframework.web.servlet.tags.form.FormTag {
	
	private TagWriter tagWriter;

	private static final String INPUT_TAG = "input";
	private static final String NAME_ATTRIBUTE = "name";
	private static final String ID_ATTRIBUTE = "id";
	private static final String TYPE_ATTRIBUTE = "type";
	private static final String TOKEN_KEY = "tokenKey";
	private static final String VALUE_ATTRIBUTE = "value";

	public FormTag() {
		setModelAttribute(Constant.FORM);
		setOnsubmit("$.blockUI({message: '<i class=\"fas fa-circle-notch fa-spin\"></i> Processing...', css: { border: 'none', padding: '30px', backgroundColor: '#FFFFFF', '-webkit-border-radius': '10px', '-moz-border-radius': '10px', color: 'black', fontSize:'32px' }});");	
	}

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		super.writeTagContent(tagWriter);
		String inputType = "hidden";
		tagWriter.startTag(INPUT_TAG);
		writeOptionalAttribute(tagWriter, TYPE_ATTRIBUTE, inputType);
		writeOptionalAttribute(tagWriter, NAME_ATTRIBUTE, TOKEN_KEY);
		writeOptionalAttribute(tagWriter, ID_ATTRIBUTE, TOKEN_KEY);
		writeOptionalAttribute(tagWriter, VALUE_ATTRIBUTE, null != pageContext.getSession()?(String) pageContext.getSession().getAttribute(TOKEN_KEY):null);
		tagWriter.endTag();

		return EVAL_BODY_INCLUDE;
	}
		
}
