/**
 * 
 */
package com.cisco.innovation.dao;

import java.util.List;
import com.cisco.innovation.model.TimeSeriesPowerData;

/**
 * @author rajagast
 *
 */
public interface TimeSeriesPowerDAO {
	public void save(TimeSeriesPowerData data);
	
	public void update(TimeSeriesPowerData data);
	
	public void delete(TimeSeriesPowerData data);
	
	public List getPowerUsageForUser(String username, String current, String end);
	
	public List getLivePowerForUser(String username);
}
