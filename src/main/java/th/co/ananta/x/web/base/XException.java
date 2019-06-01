package th.co.ananta.x.web.base;

import java.sql.SQLException;

public class XException extends Throwable {
	/**
	 *
	 */
	private static final long serialVersionUID = -710939417749467842L;
	private String code;
	private String message;

	public XException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public XException(Exception e) {
		e.printStackTrace();
		this.code = e.getClass().getName();
		this.message = e.getMessage();
		setStackTrace(e.getStackTrace());
	}

	public XException(SQLException e) {
		e.printStackTrace();
		this.code = e.getSQLState();
		this.message = e.getMessage();
		setStackTrace(e.getStackTrace());
	}

	public XException(Exception e, String message) {
		this(e);
		if (null == e.getMessage()) {
			this.message = message + " : " + e.getClass();
		} else {
			this.message = message + " : " + e.getMessage();
		}
	}

	public XException(SQLException e, String message) {
		this(e);
		if (null == e.getMessage()) {
			this.message = message + " : " + e.getClass();
		} else {
			this.message = message + " : " + e.getMessage();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
