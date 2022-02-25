package com.application.demo.model;

import java.util.List;

import lombok.Data;

@Data
public class BusinessEntry {
	
	private String displayedWhat;
	
	private String displayedWhere;
	
	private OpeningHours openingHours;
}
