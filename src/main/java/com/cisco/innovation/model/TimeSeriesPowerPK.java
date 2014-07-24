/**
 * 
 */
package com.cisco.innovation.model;

import java.io.Serializable;
import java.sql.Date;

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
	private Integer watts;
	private String dateTime;

	public TimeSeriesPowerPK(String username, Integer watts, String dateTime) {
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

	public Integer getWatts() {
		return watts;
	}

	public void setWatts(Integer watts) {
		this.watts = watts;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

}
