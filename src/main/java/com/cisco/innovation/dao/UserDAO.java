/**
 * 
 */
package com.cisco.innovation.dao;

import java.util.List;

import com.cisco.innovation.model.User;

/**
 * @author rajagast
 *
 */
public interface UserDAO {
	void save(User user);

	void update(User user);

	void delete(User user);

	User findUserByUsername(String username);

	List<User> findUsersByDeviceUUID(String uuid);
}
