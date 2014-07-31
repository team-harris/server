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
	public List<User> findUserByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> user = (List<User>) sessionFactory.getCurrentSession().createQuery("from User where username= :username").setParameter("username", username).list();
		return user;
	}

	@Override
	public List<User> findUsersByDeviceUUID(String uuid) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)sessionFactory.getCurrentSession().createQuery("from User where device_uuid = :uuid").setParameter("uuid", uuid).list();
		return users;
	}

	@Override
	public List<User> findUsersByGroup(String group) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) sessionFactory.getCurrentSession().createQuery("from User where group = :group").setParameter("group", group).list();
		return users;
	}

	@Override
	public List<String> getAllGroups() {
		@SuppressWarnings("unchecked")
		List<String> groupsList = (List<String>) sessionFactory.getCurrentSession().createQuery("select distinct u.group from User u").list();
		return groupsList;
	}

}
