package com.cisco.innovation.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cisco.innovation.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
		
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public User findUserByUsername(String username) {
		User user = (User) sessionFactory.getCurrentSession().createQuery("from users where username=" + username).list();
		return user;
	}

	@Override
	public List<User> findUsersByDeviceUUID(String uuid) {
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>)sessionFactory.getCurrentSession().createQuery("from User where device_uuid = :uuid").setParameter("uuid", uuid).list();
			return list;
	}

}
