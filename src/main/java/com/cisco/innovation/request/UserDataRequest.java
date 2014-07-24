/**
 * 
 */
package com.cisco.innovation.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author rajagast
 *
 */
public class UserDataRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("minutes")
	private Integer minutes;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

}
