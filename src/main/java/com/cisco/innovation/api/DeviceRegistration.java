/**
 * 
 */
package com.cisco.innovation.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.innovation.data.PowerDataWrapper;
import com.cisco.innovation.model.Device;
import com.cisco.innovation.model.TimeSeriesPowerData;
import com.cisco.innovation.model.TimeSeriesPowerPK;
import com.cisco.innovation.model.User;
import com.cisco.innovation.service.DeviceService;
import com.cisco.innovation.service.TimeSeriesPowerService;
import com.cisco.innovation.service.UserService;
import com.cisco.innovation.utils.Utils;

/**
 * @author rajagast
 *
 */
@Controller
@RequestMapping("/device")
public class DeviceRegistration {
	private static Logger logger = LoggerFactory
			.getLogger(DeviceRegistration.class);

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private TimeSeriesPowerService timeSeriesPowerService;

	@RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registerTest(@RequestBody Device device) {
		System.out.println(device.getUUID() + ","
				+ device.getUsers().get(0).getUsername());
		Device dev = device;
		List<User> list = dev.getUsers();
		dev.setUsers(list);
		deviceService.save(dev);
		for (User user : list) {
			user.setDevice(dev);
			userService.save(user);
		}
		logger.info("Device registered");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public ResponseEntity<Void> test(HttpEntity<String> httpEntity) {
		System.out.println("Hit!!\n" + httpEntity.getBody());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sendusage", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getPowerData(
			@RequestBody PowerDataWrapper powerData) {
		
		if (validateDeviceUUid(powerData.getUUID())) {
			List<User> users = userService.findUsersByDeviceUUID(powerData.getUUID());
			for (User user : users) {
				// Calculate running average
				// Persist again
				TimeSeriesPowerPK timeSeriesPowerPK = new TimeSeriesPowerPK(user.getUsername(), Integer.valueOf(powerData.getReading1()), Utils.getCurrentDateTime());
				TimeSeriesPowerData data = new TimeSeriesPowerData();
				data.setTimeSeriesPowerPK(timeSeriesPowerPK);
				timeSeriesPowerService.save(data);
			}
			logger.info("Device validation successful");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			logger.error("Device UUID not valid!!");
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

	}

	private boolean validateDeviceUUid(String uuid) {
		return deviceService.isUUIDValid(uuid);
	}
}
