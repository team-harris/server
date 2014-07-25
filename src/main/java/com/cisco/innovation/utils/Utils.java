package com.cisco.innovation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static Logger logger = LoggerFactory.getLogger(Utils.class);
	public static double exponentialMovingAvg(Double oldValue, double value,
			double alpha) {
		if (oldValue == null) {
			oldValue = value;
			return value;
		}
		double newValue = oldValue + alpha * (value - oldValue);
		oldValue = newValue;
		return newValue;
	}

	public static String getCurrentDateTime() {
		Date date = new Date();
		return formatDate(date);
	}

	public static String formatDate(Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(date);
		return currentTime;
	}

	public static String subtractHoursFromCurrentDate(Integer hours) {
		Date date = new Date();
		Date dateBefore = new Date(date.getTime() - (hours * 3600 * 1000));
		return formatDate(dateBefore);
	}
	
	public static Date getDateFromString(String dateString) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(dateString);
			return date;
		} catch (ParseException e) {
			logger.error("ParseException occured in getDateFromString() method");
		}
		return null;
	}
}
