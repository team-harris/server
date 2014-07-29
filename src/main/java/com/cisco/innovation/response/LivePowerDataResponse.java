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
}
