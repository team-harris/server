/**
 * 
 */
package com.cisco.innovation.response;

/**
 * @author rajagast
 *
 */
public class LivePowerDataResponse {
	private Integer responseCode;
	
	private String username;

	private double watts;
	
	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public double getWatts() {
		return watts;
	}

	public void setWatts(double watts) {
		this.watts = watts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
