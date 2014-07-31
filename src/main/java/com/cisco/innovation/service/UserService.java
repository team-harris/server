/**
 * 
 */
package com.cisco.innovation.service;

import java.util.List;

import com.cisco.innovation.model.User;

/**
 * @author rajagast
 *
 */
public interface UserService {
	void save(User user);

	void update(User user);

	void delete(User user);

	List<User> findUserByUsername(String username);
	
	List<User> findUsersByDeviceUUID(String uuid);
	
	List<User> findUsersByGroup(String group);
	
	List<String> getAllGroups();
}
