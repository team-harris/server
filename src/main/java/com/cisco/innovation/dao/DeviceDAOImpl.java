/**
 * 
 */
package com.cisco.innovation.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cisco.innovation.model.Device;

/**
 * @author rajagast
 *
 */
@Repository("deviceDao")
public class DeviceDAOImpl implements DeviceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Device device) {
		sessionFactory.getCurrentSession().save(device);
	}

	@Override
	public void update(Device device) {
		sessionFactory.getCurrentSession().update(device);
	}

	@Override
	public void delete(Device device) {
		sessionFactory.getCurrentSession().delete(device);
	}

	@Override
	public boolean isUUIDValid(String uuid) {
		@SuppressWarnings("rawtypes")
		List list = sessionFactory.getCurrentSession().createQuery("from Device where UUID = :uuid").setParameter("uuid", uuid).list();
		return !list.isEmpty();
	}
	
}
