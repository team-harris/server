/**
 * 
 */
package com.cisco.innovation.service;

import com.cisco.innovation.model.Device;

/**
 * @author rajagast
 *
 */
public interface DeviceService {
	void save(Device device);

	void update(Device device);

	void delete(Device device);

	boolean isUUIDValid(String uuid);
}
