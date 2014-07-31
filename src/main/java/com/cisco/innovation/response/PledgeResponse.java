/**
 * 
 */
package com.cisco.innovation.response;

/**
 * @author rajagast
 *
 */
public class PledgeResponse {
	private Integer responseCode;
	
	private String username;

	private Integer pledges;
	
	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPledges() {
		return pledges;
	}

	public void setPledges(Integer pledges) {
		this.pledges = pledges;
	}

}
