/**
 * 
 */
package com.cisco.innovation.service;

import com.cisco.innovation.model.User;

/**
 * @author rajagast
 *
 */
public interface UserService {
	void save(User user);

	void update(User user);

	void delete(User user);

	User findUserByUsername(String username);
}
