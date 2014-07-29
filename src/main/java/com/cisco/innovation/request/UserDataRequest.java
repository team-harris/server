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
	
	@JsonProperty("group")
	private String group;
	
	@JsonProperty("hours")
	private Integer hours;
	
	@JsonProperty("days")
	private Integer days;
	
	@JsonProperty("months")
	private Integer months;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
