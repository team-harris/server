/**
 * 
 */
package com.cisco.innovation.response;

import java.io.Serializable;

/**
 * @author rajagast
 *
 */
public class GroupPowerDataResponse extends PowerDataResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GroupPowerDataResponse() {
		super();
	}
	
	private String group;
	
	private double groupWatts;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public double getGroupWatts() {
		return groupWatts;
	}

	public void setGroupWatts(double groupWatts) {
		this.groupWatts = groupWatts;
	}

}
