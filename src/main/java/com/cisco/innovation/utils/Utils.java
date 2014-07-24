package com.cisco.innovation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
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
	
	public static String convertMinToDateString(Integer minutes) {
		Date date = new Date();
		Date dateBefore = new Date(date.getTime() - (minutes * 60000));
		return formatDate(dateBefore);
	}
}
