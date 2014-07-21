/**
 * 
 */
package com.cisco.innovation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author rajagast
 *
 */
@Entity
@Table(name = "devices")
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("uuid")
	@Id
	@Column(name = "uuid", unique = true, nullable = false)
	private String UUID;

	@JsonProperty("users")
	@OneToMany(mappedBy = "device")
	private List<User> users = new ArrayList<User>();

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
