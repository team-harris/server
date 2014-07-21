/**
 * 
 */
package com.cisco.innovation.data;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author rajagast
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerDataWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("uuid")
	String UUID;

	@JsonProperty("user1")
	Integer reading1;

	@JsonProperty("user2")
	Integer reading2;

	@JsonProperty("user3")
	Integer reading3;

	@JsonProperty("user4")
	Integer reading4;

	public Integer getReading1() {
		return reading1;
	}

	public void setReading1(Integer reading1) {
		this.reading1 = reading1;
	}

	public Integer getReading2() {
		return reading2;
	}

	public void setReading2(Integer reading2) {
		this.reading2 = reading2;
	}

	public Integer getReading3() {
		return reading3;
	}

	public void setReading3(Integer reading3) {
		this.reading3 = reading3;
	}

	public Integer getReading4() {
		return reading4;
	}

	public void setReading4(Integer reading4) {
		this.reading4 = reading4;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

}
