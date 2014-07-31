/**
 * 
 */
package com.cisco.innovation.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rajagast
 *
 */
public class GroupPowerDataResponse implements Serializable,
		Comparable<GroupPowerDataResponse> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer responseCode;

	private List<PowerDataResponse> listOfUserResponses = new ArrayList<PowerDataResponse>();

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public List<PowerDataResponse> getListOfUserResponses() {
		return listOfUserResponses;
	}

	public void setListOfUserResponses(List<PowerDataResponse> listOfUserResponses) {
		this.listOfUserResponses = listOfUserResponses;
	}

	private String businessGroup;

	private double groupWatts;

	public String getGroup() {
		return businessGroup;
	}

	public void setGroup(String group) {
		this.businessGroup = group;
	}

	public double getGroupWatts() {
		return groupWatts;
	}

	public void setGroupWatts(double groupWatts) {
		this.groupWatts = groupWatts;
	}

	@Override
	public int compareTo(GroupPowerDataResponse that) {
		if (this.groupWatts < that.groupWatts)
			return -1;
		if (this.groupWatts > that.groupWatts)
			return +1;
		return 0;
	}

}
