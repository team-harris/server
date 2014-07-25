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
public class PowerDataWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("uuid")
	String UUID;

	@JsonProperty("user1")
	Double reading1;

	@JsonProperty("user2")
	Double reading2;

	@JsonProperty("user3")
	Double reading3;

	@JsonProperty("user4")
	Double reading4;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public Double getReading1() {
		return reading1;
	}

	public void setReading1(Double reading1) {
		this.reading1 = Math.abs(reading1);
	}

	public Double getReading2() {
		return reading2;
	}

	public void setReading2(Double reading2) {
		this.reading2 = Math.abs(reading2);
	}

	public Double getReading3() {
		return reading3;
	}

	public void setReading3(Double reading3) {
		this.reading3 = Math.abs(reading3);
	}

	public Double getReading4() {
		return reading4;
	}

	public void setReading4(Double reading4) {
		this.reading4 = Math.abs(reading4);
	}

}
