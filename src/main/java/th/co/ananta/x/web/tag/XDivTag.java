package th.co.ananta.x.web.tag;

import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class XDivTag extends AbstractHtmlElementTag {

	public static final String FIELD_ATTRIBUTE = "field";

	private static final String DIV_TAG = "div";

	private TagWriter tagWriter;

	private String field;

	private String permission = "3";

	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;
		String xUri = getRequestContext().getRequestUri();

//		Link link = XCache.getLinks().get(xUri + Constant.SLATE + Constant.DEFAULT.USER_GROUP_NONE);
//		if (link != null) {
//			this.permission = link.getPermission();
//			for (Field field : link.getFields()) {
//				if (field.getId().equals(getField()) && field.getGroupId().equals(Constant.DEFAULT.USER_GROUP_NONE)) {
//					this.permission = field.getPermission();
//					break;
//				}
//			}
//			if ("1".equals(this.permission)) {
//				return SKIP_BODY;
//			}
//		}

		this.tagWriter.startTag(DIV_TAG);
		this.tagWriter.writeAttribute(CLASS_ATTRIBUTE, getCssClass());
		this.tagWriter.forceBlock();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		if ("1".equals(this.permission)) {
			this.release();
			return SKIP_BODY;
		}
		this.tagWriter.endTag();
		this.release();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void release() {
		super.release();
		this.tagWriter = null;
		this.permission = "3";
		this.field = null;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
