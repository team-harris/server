/**
 * 
 */
package com.cisco.innovation.dao;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public List getPowerUsageForUser(String username, String current, String end) {
		Query query = sessionFactory.getCurrentSession().createQuery("from TimeSeriesPowerData where timeSeriesPowerPK.dateTime <=:param1 AND timeSeriesPowerPK.dateTime >= :param2 AND username= :username");
		query.setParameter("param1",current);
		query.setParameter("param2",end);
		query.setParameter("username", username);
		List powerUsedList=query.list();
		return powerUsedList;
	}

}
