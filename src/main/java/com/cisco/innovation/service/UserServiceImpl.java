/**
 * 
 */
package com.cisco.innovation.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.innovation.model.User;

/**
 * @author rajagast
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	@Transactional
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	@Transactional
	public User findUserByUsername(String username) {
		User user = (User) sessionFactory.getCurrentSession()
				.createQuery("from users where username=" + username).list();
		return user;
	}

}
