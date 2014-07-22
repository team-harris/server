/**
 * 
 */
package com.cisco.innovation.dao;

import com.cisco.innovation.model.Device;

/**
 * @author rajagast
 *
 */
public interface DeviceDAO {
	public void save(Device device);

	public void update(Device device);

	public void delete(Device device);
	
	public boolean isUUIDValid(String uuid);
}
