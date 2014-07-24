/**
 * 
 */
package com.cisco.innovation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.innovation.dao.UserDAO;
import com.cisco.innovation.model.User;

/**
 * @author rajagast
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	@Transactional
	public User findUserByUsername(String username) {
		return userDAO.findUserByUsername(username);
	}

	@Override
	@Transactional
	public List<User> findUsersByDeviceUUID(String uuid) {
		return userDAO.findUsersByDeviceUUID(uuid);
	}

}
