/**
 * 
 */
package com.cisco.innovation.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cisco.innovation.model.TimeSeriesPowerData;

/**
 * @author rajagast
 *
 */
@Repository("timeSeriesPowerDAO")
public class TimeSeriesPowerDAOImpl implements TimeSeriesPowerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(TimeSeriesPowerData data) {
		sessionFactory.getCurrentSession().save(data);
	}

	@Override
	public void update(TimeSeriesPowerData data) {
		sessionFactory.getCurrentSession().update(data);
	}

	@Override
	public void delete(TimeSeriesPowerData data) {
		sessionFactory.getCurrentSession().delete(data);
	}

}
