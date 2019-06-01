package th.co.ananta.x.web.base;


public class GenericValidator {

	public static boolean isValidEmailAddress(final String email) {
		final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		final java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		final java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean validSmsMobilePhoneNumberInput(String mobileNumber) {
		boolean isValid = false;

		if (!org.apache.commons.lang3.StringUtils.isNumeric(mobileNumber))
			return isValid;

		final String phoneNumberFormat = "01,06,08,09,02";

		if (phoneNumberFormat != null && !phoneNumberFormat.equals("")) {
			if (mobileNumber.length() == 10) {
				if (mobileNumber != null && mobileNumber.trim().length() > 0) {
					mobileNumber = mobileNumber.substring(0, 2);
					if (phoneNumberFormat.indexOf(mobileNumber) >= 0) {
						isValid = true;
					} else {
						isValid = false;
					}
				} else {
					isValid = true;
				}
			} else {
				isValid = false;
			}

		} else {
			return true;
		}

		return isValid;
	}

}
