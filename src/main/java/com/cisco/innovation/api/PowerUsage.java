/**
 * 
 */
package com.cisco.innovation.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cisco.innovation.data.PowerDataWrapper;

/**
 * @author rajagast Class whose methods are exposed to the device
 *
 */

@Controller
@RequestMapping("/powermonitor")
public class PowerUsage {
	private static Logger logger = LoggerFactory.getLogger(PowerUsage.class);

	@RequestMapping(method = RequestMethod.POST, value = "/sendusage", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> getPowerData(
			@RequestBody PowerDataWrapper powerData) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
