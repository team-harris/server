/**
 * 
 */
package com.cisco.innovation.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.innovation.model.TimeSeriesPowerPK;
import com.cisco.innovation.request.UserDataRequest;
import com.cisco.innovation.response.PowerDataResponse;
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

	@RequestMapping(method = RequestMethod.POST, value = "/getdata", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PowerDataResponse getPowerData(@RequestBody UserDataRequest request) {
		PowerDataResponse response;
		String username = request.getUsername();
		logger.debug("Request obtained for user" + username
				+ " for " + request.getHours() + " hours");

		String currentDateTime = Utils.getCurrentDateTime();
		String previousDateTime = Utils.subtractHoursFromCurrentDate(request
				.getHours());
		@SuppressWarnings("unchecked")
		List<TimeSeriesPowerPK> powerDataList = timeSeriesPowerService
				.getPowerUsageForUser(username, currentDateTime,
						previousDateTime);
		if (!powerDataList.isEmpty()) {
			logger.debug(powerDataList.size() + " values obtained for user " + username);
			response = computeAverages(powerDataList, currentDateTime, previousDateTime);
			response.setResponseCode(HttpStatus.OK.value());
			return response;
		} else {
			logger.debug("ZERO values obtained for user " + username);
			response = new PowerDataResponse();
			response.setResponseCode(HttpStatus.BAD_REQUEST.value());
			return response;
		}

	}
	
	// Consider GET
	@RequestMapping(method = RequestMethod.POST, value = "/getlivedata", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody Double getLivePowerData() {
		// Code for producing live data for a user goes here
		// Get latest record from DB for a user
		// Construct response object and send response code
		return null;
	}

	private PowerDataResponse computeAverages(List<TimeSeriesPowerPK> powerDataList,
			String currentDateTime, String previousDateTime) {
		HashMap<Integer, Double> hourAvgMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> daysAvgMap = new HashMap<Integer, Double>();
		
		/*DescriptiveStatistics statsHours = new DescriptiveStatistics();
		statsHours.setWindowSize(powerDataList.size());
		
		DescriptiveStatistics statsDays = new DescriptiveStatistics();
		statsDays.setWindowSize(powerDataList.size());*/
		
		for (TimeSeriesPowerPK powerData : powerDataList) {
			Date dateFromString = Utils.getDateFromString(powerData
					.getDateTime());
			if (dateFromString.after(Utils.getDateFromString(previousDateTime))
					&& dateFromString.before(Utils
							.getDateFromString(currentDateTime))) {
				logger.debug("Power at " + dateFromString + " considered for average");
				int hour = dateFromString.getHours();
				int day = dateFromString.getDay();
				if (hourAvgMap.containsKey(hour)) {
					hourAvgMap.put(hour, Utils.exponentialMovingAvg(hourAvgMap.get(hour),
									powerData.getWatts(), 0.5));
				} else {
					hourAvgMap.put(hour, powerData.getWatts());
				}
				
				if (daysAvgMap.containsKey(day)) {
					daysAvgMap.put(day, Utils.exponentialMovingAvg(daysAvgMap.get(day),
									powerData.getWatts(), 0.5));
				} else {
					daysAvgMap.put(day, powerData.getWatts());
				}

			}
		}
		PowerDataResponse response = new PowerDataResponse();
		response.setDaysMap(daysAvgMap);
		response.setHoursMap(hourAvgMap);
		return response;
	}
}
