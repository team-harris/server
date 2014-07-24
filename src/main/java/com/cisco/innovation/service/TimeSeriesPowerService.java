/**
 * 
 */
package com.cisco.innovation.service;

import com.cisco.innovation.model.TimeSeriesPowerData;

/**
 * @author rajagast
 *
 */
public interface TimeSeriesPowerService {
	public void save(TimeSeriesPowerData data);

	public void update(TimeSeriesPowerData data);

	public void delete(TimeSeriesPowerData data);
}
