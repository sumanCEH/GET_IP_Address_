package com.capgemini.service;

import jakarta.servlet.http.HttpServletRequest;

public interface RequestService {

	public String getClientIPAddress(HttpServletRequest request);
}
