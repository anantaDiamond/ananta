package th.co.ananta.x.web.tag;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class XImgTag extends AbstractHtmlElementTag {

	private TagWriter tagWriter;

	private static final String A_TAG = "img";

	// attr
	public static final String SRC_ATTRIBUTE = "src";
	public static final String HEIGHT_ATTRIBUTE = "height";
	public static final String WIDTH_ATTRIBUTE = "width";
	public static final String ID_ATTRIBUTE = "id";
	public static final String ALT_ATTRIBUTE = "alt";

	private String src;
	private String height;
	private String width;
	private String alt;
	
	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		this.tagWriter.startTag(A_TAG);
		this.tagWriter.writeAttribute(SRC_ATTRIBUTE,getRequestContext().getContextPath() + getSrc());
		this.tagWriter.writeAttribute(HEIGHT_ATTRIBUTE, getHeight());
		this.tagWriter.writeAttribute(WIDTH_ATTRIBUTE, getWidth());
		this.tagWriter.writeAttribute(CLASS_ATTRIBUTE, getCssClass());
		this.tagWriter.writeAttribute(ID_ATTRIBUTE, getId());
		this.tagWriter.writeAttribute(ONCLICK_ATTRIBUTE, getOnclick());
		this.tagWriter.writeAttribute(STYLE_ATTRIBUTE, getCssStyle());
		this.tagWriter.writeAttribute(ALT_ATTRIBUTE, getAlt());
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
		this.src = null;
		this.height = null;
		this.width = null;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	
}
