/**
 * 
 */
package com.cisco.innovation.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.innovation.request.UserDataRequest;
import com.cisco.innovation.service.TimeSeriesPowerService;
import com.cisco.innovation.utils.Utils;

/**
 * @author rajagast Class whose methods are exposed to the device
 *
 */

@Controller
@RequestMapping("/powermonitor")
public class PowerUsage {
	private static Logger logger = LoggerFactory.getLogger(PowerUsage.class);
	
	@Autowired
	private TimeSeriesPowerService timeSeriesPowerService;

	@RequestMapping(method = RequestMethod.POST, value = "/getdata", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getPowerData(
			@RequestBody UserDataRequest request) {
		logger.debug("Request obtained for user" + request.getUsername() + " for " + request.getMinutes() + " minutes");
		
		System.out.println(timeSeriesPowerService.getPowerUsageForUser(request.getUsername(), Utils.getCurrentDateTime(), Utils.convertMinToDateString(request.getMinutes())));;
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
