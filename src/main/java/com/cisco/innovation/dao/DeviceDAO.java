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
	void save(Device device);

	void update(Device device);

	void delete(Device device);
}
