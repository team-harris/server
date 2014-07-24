package com.cisco.innovation.utils;

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
		Date dt = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}
}
