/**
 * 
 */
package com.cisco.innovation.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author rajagast
 *
 */
@Embeddable
public class TimeSeriesPowerPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private Double watts;
	private String dateTime;

	public TimeSeriesPowerPK() {

	}

	public TimeSeriesPowerPK(String username, Double watts, String dateTime) {
		this.username = username;
		this.watts = watts;
		this.dateTime = dateTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double getWatts() {
		return watts;
	}

	public void setWatts(Double watts) {
		this.watts = watts;
	}

}
