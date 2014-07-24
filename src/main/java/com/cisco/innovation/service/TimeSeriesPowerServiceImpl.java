/**
 * 
 */
package com.cisco.innovation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.innovation.dao.TimeSeriesPowerDAO;
import com.cisco.innovation.model.TimeSeriesPowerData;

/**
 * @author rajagast
 *
 */
@Service("timeSeriesPowerService")
public class TimeSeriesPowerServiceImpl implements TimeSeriesPowerService{

	@Autowired
	private TimeSeriesPowerDAO timeSeriesPowerDAO;
	
	@Override
	@Transactional
	public void save(TimeSeriesPowerData data) {
		timeSeriesPowerDAO.save(data);
	}

	@Override
	@Transactional
	public void update(TimeSeriesPowerData data) {
		timeSeriesPowerDAO.update(data);
	}

	@Override
	@Transactional
	public void delete(TimeSeriesPowerData data) {
		timeSeriesPowerDAO.delete(data);
	}

}
