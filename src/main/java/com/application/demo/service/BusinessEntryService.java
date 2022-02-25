package com.application.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.application.demo.model.BusinessEntry;
import com.application.demo.model.OpeningHours;
import com.application.demo.model.WorkingHours;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BusinessEntryService implements BusinessEntryServiceInterface{
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	@Override
	public BusinessEntry getPlace(String place_id) {
		String url = "https://storage.googleapis.com/coding-session-rest-api/"+place_id;
		RestTemplate restTemplate = new RestTemplate();
		Object result = restTemplate.getForObject(url, Object.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map = objectMapper.convertValue(result, Map.class);
		BusinessEntry businessEntry = mapBusinessEntry(map);
		return businessEntry;
	}
	
	@SuppressWarnings("unchecked")
	private BusinessEntry mapBusinessEntry(Map<String, Object> map) {
		BusinessEntry businessEntry= new BusinessEntry();
		businessEntry.setDisplayedWhat(map.get("displayed_what").toString());
		businessEntry.setDisplayedWhere(map.get("displayed_where").toString());
		Map<String, Object> mapOpeningHours = new HashMap<String, Object>();
		mapOpeningHours = objectMapper.convertValue(map.get("opening_hours"), Map.class);
		OpeningHours openingHours = new OpeningHours();
		Map<String,  ArrayList<WorkingHours>> days = new HashMap<String,  ArrayList<WorkingHours>>();
		days = objectMapper.convertValue(mapOpeningHours.get("days"), Map.class);
		openingHours.setDays(days); 
		openingHours.setClosedOnHolidays(mapOpeningHours.get("closed_on_holidays") != null ? mapOpeningHours.get("closed_on_holidays").toString() : null);
		openingHours.setOpenByArrangement(mapOpeningHours.get("open_by_arrangement") != null ? mapOpeningHours.get("open_by_arrangement").toString() : null);
		businessEntry.setOpeningHours(openingHours);
		return businessEntry;
	}

}
