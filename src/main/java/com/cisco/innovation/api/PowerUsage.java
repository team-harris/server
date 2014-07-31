/**
 * 
 */
package com.cisco.innovation.api;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cisco.innovation.model.User;
import com.cisco.innovation.request.UserDataRequest;
import com.cisco.innovation.response.AllGroupsDataResponse;
import com.cisco.innovation.response.GroupPowerDataResponse;
import com.cisco.innovation.response.LivePowerDataResponse;
import com.cisco.innovation.response.PledgeResponse;
import com.cisco.innovation.response.PowerDataResponse;
import com.cisco.innovation.service.TimeSeriesPowerService;
import com.cisco.innovation.service.UserService;
import com.cisco.innovation.utils.Utils;

/**
 * @author rajagast 
 * Class whose methods are exposed to the device
 *
 */

@Controller
@RequestMapping("/powermonitor")
public class PowerUsage {
	private static Logger logger = LoggerFactory.getLogger(PowerUsage.class);

	@Autowired
	private TimeSeriesPowerService timeSeriesPowerService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/getuserdata", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PowerDataResponse getPowerDataForUser(@RequestBody UserDataRequest request) {
		PowerDataResponse response;
		String username = request.getUsername();
		logger.debug("Request obtained for user" + username	+ " for " + request.getHours() + " hours");

		String currentDateTime = Utils.getCurrentDateTime();
		String previousDateTime = subtractAndGetPreviousDate(request);
		@SuppressWarnings("unchecked")
		List<TimeSeriesPowerPK> powerDataList = timeSeriesPowerService
				.getPowerUsageForUser(username, currentDateTime,
						previousDateTime);
		response = queryUsageAndGenerateResponse(username, currentDateTime, previousDateTime, powerDataList, request);
		return response;

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getgroupdata", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GroupPowerDataResponse getPowerDataForGroup(@RequestBody UserDataRequest request) {
		GroupPowerDataResponse response;
		Map<Integer, Double> hoursMap = null;
		Map<Integer, Double> daysMap = null;
		Map<Integer, Double> monthsMap = null;
		String group = request.getGroup();
		response = calculateForGroup(group, hoursMap, daysMap, monthsMap, request);
		return response;
		
	}
	
	private GroupPowerDataResponse calculateForGroup(String group,
			Map<Integer, Double> hoursMap, Map<Integer, Double> daysMap,
			Map<Integer, Double> monthsMap, UserDataRequest request) {
		Double groupTotal = 0.0;
		logger.debug("Request obtained for group" + group	+ " for " + request.getHours() + " hours");
		String currentDateTime = Utils.getCurrentDateTime();
		String previousDateTime = subtractAndGetPreviousDate(request);
		List<User> users = userService.findUsersByGroup(group);
		if (users.isEmpty()) {
			return generateGroupFailureResponse(group);
		} else {
			GroupPowerDataResponse response = new GroupPowerDataResponse();
			for (User user : users) {
				Double userTotal = 0.0;
				@SuppressWarnings("unchecked")
				List<TimeSeriesPowerPK> powerDataList = timeSeriesPowerService.getPowerUsageForUser(user.getUsername(), currentDateTime,
								previousDateTime);
				PowerDataResponse res = queryUsageAndGenerateResponse(user.getUsername(), currentDateTime, previousDateTime, powerDataList, request);
				if (res.getResponseCode() != 400) {
					if (res.getHoursMap().size() > 0) {
						hoursMap = res.getHoursMap();
						userTotal = sumMapValues(hoursMap);
					}
					if (res.getDaysMap().size() > 0) {
						daysMap = res.getDaysMap();
						userTotal = sumMapValues(daysMap);
					}
					if (res.getMonthsMap().size() > 0) {
						monthsMap = res.getMonthsMap();
						userTotal = sumMapValues(monthsMap);
					}
					groupTotal += userTotal;
				}
				response.getListOfUserResponses().add(res);
			}
			response.setResponseCode(HttpStatus.OK.value());
			response.setGroupWatts(groupTotal);
			response.setGroup(group);
			return response;
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/getallgroups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AllGroupsDataResponse getAllGroupsData(@RequestBody UserDataRequest request) {
		AllGroupsDataResponse response;
		GroupPowerDataResponse groupResponse;
		Map<Integer, Double> hoursMap = null;
		Map<Integer, Double> daysMap = null;
		Map<Integer, Double> monthsMap = null;
		List<String> groups = userService.getAllGroups();
		if (!groups.isEmpty()) {
			response = new AllGroupsDataResponse();
			for (String group : groups) {
				groupResponse = calculateForGroup(group, hoursMap, daysMap, monthsMap, request);
				response.getListOfGroups().add(groupResponse);
			}
			Collections.sort(response.getListOfGroups(), Collections.reverseOrder());
			response.setResponseCode(HttpStatus.OK.value());
			return response;
		} else {
			response = new AllGroupsDataResponse();
			response.setResponseCode(HttpStatus.BAD_REQUEST.value());
			return response;
		}
	}

	private Double sumMapValues(Map<Integer, Double> map) {
		Double sum = 0.0;
		for (Double value : map.values()) {
			sum += value;
		}
		return sum;
	}

	private PowerDataResponse queryUsageAndGenerateResponse(String username,
			String currentDateTime, String previousDateTime, List<TimeSeriesPowerPK> powerDataList, UserDataRequest request) {
		PowerDataResponse response;
		if (!powerDataList.isEmpty() && previousDateTime != null) {
			logger.debug(powerDataList.size() + " values obtained for user " + username);
			response = computeAverages(powerDataList, currentDateTime, previousDateTime, request);
			response.setResponseCode(HttpStatus.OK.value());
			response.setUsername(username);
			return response;
		} else {
			return generateUserFailureResponse(username);
		}
	}

	private PowerDataResponse generateUserFailureResponse(String value) {
		PowerDataResponse response;
		logger.debug("ZERO values obtained for user/group " + value);
		response = new PowerDataResponse();
		response.setResponseCode(HttpStatus.BAD_REQUEST.value());
		return response;
	}
	
	private GroupPowerDataResponse generateGroupFailureResponse(String value) {
		GroupPowerDataResponse response;
		logger.debug("ZERO values obtained for user/group " + value);
		response = new GroupPowerDataResponse();
		response.setResponseCode(HttpStatus.BAD_REQUEST.value());
		return response;
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
					.getDays());
		} else if (months > 0 && hours == 0 && days == 0) {
			return Utils.subtractMonthsFromCurrentDate(request
				.getMonths());
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
		response.setUsername(username);
		Double watts = list.get(0).getWatts();
		response.setWatts(watts);
		logger.info("Live data for user " + username + " is" + watts + " watts obtained at: " + list.get(0).getDateTime());
		return response;
	}

	private PowerDataResponse computeAverages(List<TimeSeriesPowerPK> powerDataList,
			String currentDateTime, String previousDateTime, UserDataRequest request) {
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
					&& dateFromString.before(Utils.getDateFromString(currentDateTime))) {
				logger.debug("Power at " + dateFromString + " considered for average");
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateFromString);
				int hours = (int) ((Utils.getDateFromString(currentDateTime).getTime() - dateFromString.getTime()) / (1000 * 3600));
				int days = (int) ((Utils.getDateFromString(currentDateTime).getTime() - dateFromString.getTime()) / (1000 * 3600 * 24));
				int months = Utils.getMonthsDifference(Utils.getDateFromString(currentDateTime), dateFromString);
				
				// TODO: Year
				if (request.getHours() > 0 && request.getDays() == 0 && request.getMonths() == 0) {
					if (hourAvgMap.containsKey(hours)) {
						hourAvgMap.put(hours, Utils.exponentialMovingAvg(hourAvgMap.get(hours),
										powerData.getWatts(), 0.5));
					} else {
						hourAvgMap.put(hours, powerData.getWatts());
					}
				} else if (request.getDays() > 0 && request.getHours() == 0 && request.getMonths() == 0) {
					if (daysAvgMap.containsKey(days)) {
						daysAvgMap.put(days, Utils.exponentialMovingAvg(daysAvgMap.get(days),
										powerData.getWatts(), 0.5));
					} else {
						daysAvgMap.put(days, powerData.getWatts());
					}
				} else if (request.getMonths() > 0 && request.getHours() == 0 && request.getDays() == 0) {
					if (monthsAvgMap.containsKey(months)) {
						monthsAvgMap.put(months, Utils.exponentialMovingAvg(monthsAvgMap.get(months),
										powerData.getWatts(), 0.5));
					} else {
						monthsAvgMap.put(months, powerData.getWatts());
					}
				}
			}
		}
		PowerDataResponse response = new PowerDataResponse();
		response.setUsername(request.getUsername());
		response.setDaysMap(daysAvgMap);
		response.setHoursMap(hourAvgMap);
		response.setMonthsMap(monthsAvgMap);
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pledge/{username}")
	public @ResponseBody PledgeResponse updatePledge(@PathVariable String username) {
		List<User> userList = userService.findUserByUsername(username);
		PledgeResponse response = new PledgeResponse();
		if (!userList.isEmpty() || userList.size() != 1) {
			logger.info("User " + username + " found");
			User user = userList.get(0);
			user.setPledges(user.getPledges() + 1);
			userService.update(user);
			response.setUsername(username);
			response.setPledges(user.getPledges());
			response.setResponseCode(HttpStatus.OK.value());
			return response;
		} else {
			response.setResponseCode(HttpStatus.BAD_REQUEST.value());
			return response;
		}
	}
}
