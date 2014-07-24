/**
 * 
 */
package com.cisco.innovation.dao;

import com.cisco.innovation.model.TimeSeriesPowerData;

/**
 * @author rajagast
 *
 */
public interface TimeSeriesPowerDAO {
	public void save(TimeSeriesPowerData data);
	
	public void update(TimeSeriesPowerData data);
	
	public void delete(TimeSeriesPowerData data);
}
