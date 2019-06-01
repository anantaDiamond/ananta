package th.co.ananta.x.web.tag;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class XATag extends AbstractHtmlElementTag {

	private TagWriter tagWriter;

	private static final String A_TAG = "a";

	// attr
	public static final String HREF_ATTRIBUTE = "href";
	public static final String CLASS_ATTRIBUTE = "class";

	private String href;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		this.tagWriter.startTag(A_TAG);
		this.tagWriter.writeAttribute(HREF_ATTRIBUTE, getRequestContext().getContextPath() + getHref());
		this.tagWriter.writeAttribute(CLASS_ATTRIBUTE, getCssClass());
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
		this.href = null;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

}
