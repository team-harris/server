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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cisco.innovation.model.TimeSeriesPowerPK;
import com.cisco.innovation.request.UserDataRequest;
import com.cisco.innovation.response.LivePowerDataResponse;
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
		String previousDateTime = subtractAndGetPreviousDate(request);
		@SuppressWarnings("unchecked")
		List<TimeSeriesPowerPK> powerDataList = timeSeriesPowerService
				.getPowerUsageForUser(username, currentDateTime,
						previousDateTime);
		if (!powerDataList.isEmpty() && previousDateTime == null) {
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

	public String subtractAndGetPreviousDate(UserDataRequest request) {
		double hours = request.getHours();
		double days = request.getDays();
		double months = request.getMonths();
		if (hours > 0 && days == 0 && months == 0) {
			return Utils.subtractHoursFromCurrentDate(request
					.getHours());
		} else if (days > 0 && hours == 0 && months == 0) {
			return Utils.subtractDaysFromCurrentDate(request
					.getHours());
		} else if (months > 0 && hours == 0 && days == 0) {
			return Utils.subtractMonthsFromCurrentDate(request
				.getHours());
		} else {
			logger.error("Invalid request");
			return null;
		}
	}
	
	// Consider GET
	@RequestMapping(method = RequestMethod.GET, value = "/getlivedata/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LivePowerDataResponse getLivePowerData(@PathVariable String username) {
		// Code for producing live data for a user goes here
		// Get latest record from DB for a user
		// Construct response object and send response code
		@SuppressWarnings("unchecked")
		List<TimeSeriesPowerPK> list = (List<TimeSeriesPowerPK>) timeSeriesPowerService.getLivePowerForUser(username);
		LivePowerDataResponse response = new LivePowerDataResponse();
		if (list == null || list.size() != 1) {
			logger.error("No live data!");
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return response;
		}
		response.setResponseCode(HttpStatus.OK.value());
		Double watts = list.get(0).getWatts();
		response.setWatts(watts);
		logger.info("Live data for user " + username + " is" + watts + " watts obtained at: " + list.get(0).getDateTime());
		return response;
	}

	private PowerDataResponse computeAverages(List<TimeSeriesPowerPK> powerDataList,
			String currentDateTime, String previousDateTime) {
		HashMap<Integer, Double> hourAvgMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> daysAvgMap = new HashMap<Integer, Double>();
		HashMap<Integer, Double> monthsAvgMap = new HashMap<Integer, Double>();
		
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
				int date = dateFromString.getDate();
				int month = dateFromString.getMonth();
				int year = dateFromString.getYear();
				if (hourAvgMap.containsKey(hour)) {
					hourAvgMap.put(hour, Utils.exponentialMovingAvg(hourAvgMap.get(hour),
									powerData.getWatts(), 0.5));
				} else {
					hourAvgMap.put(hour, powerData.getWatts());
				}
				
				if (daysAvgMap.containsKey(date)) {
					daysAvgMap.put(date, Utils.exponentialMovingAvg(daysAvgMap.get(date),
									powerData.getWatts(), 0.5));
				} else {
					daysAvgMap.put(date, powerData.getWatts());
				}
				
				if (monthsAvgMap.containsKey(month)) {
					monthsAvgMap.put(month, Utils.exponentialMovingAvg(monthsAvgMap.get(month),
									powerData.getWatts(), 0.5));
				} else {
					monthsAvgMap.put(month, powerData.getWatts());
				}

			}
		}
		PowerDataResponse response = new PowerDataResponse();
		response.setDaysMap(daysAvgMap);
		response.setHoursMap(hourAvgMap);
		return response;
	}
}
