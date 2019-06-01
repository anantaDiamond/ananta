package th.co.ananta.x.web.tag;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class XScriptTag extends AbstractHtmlElementTag {

	private TagWriter tagWriter;

	private static final String A_TAG = "script";

	// attr
	public static final String TYPE_ATTRIBUTE = "type";
	public static final String SRC_ATTRIBUTE = "src";

	private String type;
	private String src;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		this.tagWriter.startTag(A_TAG);
		this.tagWriter.writeAttribute(TYPE_ATTRIBUTE, getType());
		this.tagWriter.writeAttribute(SRC_ATTRIBUTE, getRequestContext().getContextPath() + getSrc());
		this.tagWriter.forceBlock();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		this.tagWriter.endTag();
		this.release();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void release() {
		super.release();
		this.tagWriter = null;
		this.type = null;
		this.src = null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
