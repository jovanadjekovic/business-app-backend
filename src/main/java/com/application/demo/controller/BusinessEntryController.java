package com.application.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.application.demo.service.BusinessEntryServiceInterface;
import com.fasterxml.jackson.databind.util.JSONPObject;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/place-mgmt")
public class BusinessEntryController {
	
	@Autowired
	private BusinessEntryServiceInterface businessEntryService;
	
	@GetMapping("/place-list/place/{place_id}")
	public Object getPlace(@PathVariable String place_id) {
		Object result = businessEntryService.getPlace(place_id);
		return result;
	}

}
