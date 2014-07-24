/**
 * 
 */
package com.cisco.innovation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cisco.innovation.dao.DeviceDAO;
import com.cisco.innovation.model.Device;

/**
 * @author rajagast
 *
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceDAO deviceDao;

	public void setDeviceDao(DeviceDAO deviceDao) {
		this.deviceDao = deviceDao;
	}

	@Override
	@Transactional
	public void save(Device device) {
		deviceDao.save(device);
	}

	@Override
	@Transactional
	public void update(Device device) {
		deviceDao.update(device);
	}

	@Override
	@Transactional
	public void delete(Device device) {
		deviceDao.delete(device);
	}

	@Override
	@Transactional
	public boolean isUUIDValid(String uuid) {
		return deviceDao.isUUIDValid(uuid);
	}

}
