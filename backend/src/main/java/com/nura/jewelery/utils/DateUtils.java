package com.nura.jewelery.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private DateUtils() {

	}

	public static Date addDaysBsdOnCategory(Date initialDate, int duration, String category) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(initialDate);
		switch (category) {
		case Constants.CAL_DAYS:
			cal.add(Calendar.DATE, duration);
			break;
		case Constants.CAL_MONTHS:
			cal.add(Calendar.MONTH, duration);
			break;
		case Constants.CAL_YEAR:
			cal.add(Calendar.YEAR, duration);
			break;

		default:
			break;
		}

		return cal.getTime();
	}

}
