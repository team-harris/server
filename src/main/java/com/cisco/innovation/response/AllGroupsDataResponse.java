package com.cisco.innovation.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllGroupsDataResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int responseCode;
	
	private List<GroupPowerDataResponse> listOfGroups = new ArrayList<GroupPowerDataResponse>();

	public List<GroupPowerDataResponse> getListOfGroups() {
		return listOfGroups;
	}

	public void setListOfGroups(List<GroupPowerDataResponse> listOfGroups) {
		this.listOfGroups = listOfGroups;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
