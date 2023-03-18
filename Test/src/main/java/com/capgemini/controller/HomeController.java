package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.service.RequestService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("/")
	public String index(HttpServletRequest request) {
		String  clientIPaddress = requestService.getClientIPAddress(request);

		return clientIPaddress;
	}

}
