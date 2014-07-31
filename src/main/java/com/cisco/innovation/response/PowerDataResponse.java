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
public class PowerDataResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer responseCode;
	private String username;
	private Map<Integer, Double> hoursMap;
	private Map<Integer, Double> daysMap;
	private Map<Integer, Double> monthsMap;

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

}
