package th.co.ananta.x.web.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class Base {

	@Autowired
	private MessageSource messages;

	public String getMessage(String key) {
		return messages.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
	public String getMessage(String key, Object[] param) {
		return messages.getMessage(key, param, LocaleContextHolder.getLocale());
	}
}
