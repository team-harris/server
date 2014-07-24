/**
 * 
 */
package com.cisco.innovation.data;

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
	String reading1;

	@JsonProperty("user2")
	String reading2;

	@JsonProperty("user3")
	String reading3;

	@JsonProperty("user4")
	String reading4;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getReading1() {
		return reading1;
	}

	public void setReading1(String reading1) {
		this.reading1 = reading1;
	}

	public String getReading2() {
		return reading2;
	}

	public void setReading2(String reading2) {
		this.reading2 = reading2;
	}

	public String getReading3() {
		return reading3;
	}

	public void setReading3(String reading3) {
		this.reading3 = reading3;
	}

	public String getReading4() {
		return reading4;
	}

	public void setReading4(String reading4) {
		this.reading4 = reading4;
	}

}
