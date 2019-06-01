package th.co.ananta.x.web.tag;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class XLinkTag extends AbstractHtmlElementTag {

	private TagWriter tagWriter;

	private static final String A_TAG = "link";

	// attr
	public static final String HREF_ATTRIBUTE = "href";
	public static final String REL_ATTRIBUTE = "rel";

	private String href;
	private String rel;

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		this.tagWriter.startTag(A_TAG);
		this.tagWriter.writeAttribute(REL_ATTRIBUTE, getRel());
		this.tagWriter.writeAttribute(HREF_ATTRIBUTE, getRequestContext().getContextPath() + getHref());
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

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
