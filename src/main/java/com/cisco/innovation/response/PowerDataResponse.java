/**
 * 
 */
package com.cisco.innovation.response;

import java.io.Serializable;
import java.util.Map;

/**
 * @author rajagast
 *
 */
public class PowerDataResponse implements Serializable, Comparable<PowerDataResponse>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer responseCode;
	private String username;
	private Map<Integer, Double> hoursMap;
	private Map<Integer, Double> daysMap;
	private Map<Integer, Double> monthsMap;
	private double total;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Map<Integer, Double> getHoursMap() {
		return hoursMap;
	}

	public void setHoursMap(Map<Integer, Double> hoursMap) {
		this.hoursMap = hoursMap;
	}

	public Map<Integer, Double> getDaysMap() {
		return daysMap;
	}

	public void setDaysMap(Map<Integer, Double> daysMap) {
		this.daysMap = daysMap;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public Map<Integer, Double> getMonthsMap() {
		return monthsMap;
	}

	public void setMonthsMap(Map<Integer, Double> monthsMap) {
		this.monthsMap = monthsMap;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int compareTo(PowerDataResponse that) {
		if (this.total < that.total)
			return -1;
		if (this.total > that.total)
			return +1;
		return 0;
	}

}
