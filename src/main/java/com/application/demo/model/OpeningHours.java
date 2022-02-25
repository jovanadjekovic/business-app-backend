package com.application.demo.model;

import java.util.ArrayList;
import java.util.Map;

import lombok.Data;

@Data
public class OpeningHours {
	
	private String closedOnHolidays;
	
	private String openByArrangement;
	
	private Map<String, ArrayList<WorkingHours>> days;

}
