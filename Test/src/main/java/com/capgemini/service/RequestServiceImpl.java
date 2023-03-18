package com.capgemini.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RequestServiceImpl implements RequestService {

	
	private  Logger logger =  LoggerFactory.getLogger(RequestServiceImpl.class);
	private final String lOCAL_HOST_IPV4 = "127.0.0.1";
	private final String lOCAL_HOST_IPV6= "0:0:0:0:0:0:0:1";
	private final String unknown ="unknown";
	
	@Override
	public String getClientIPAddress(HttpServletRequest request) {
		String clientIPaddress = request.getHeader("X-Forwarded-For");
		if (!StringUtils.hasLength(clientIPaddress) ||  unknown.equals(clientIPaddress)) {
			clientIPaddress = request.getHeader("Proxy-Client-IP");
		}
		if (!StringUtils.hasLength(clientIPaddress) || unknown.equals(clientIPaddress)) {
			clientIPaddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!StringUtils.hasLength(clientIPaddress) || unknown.equals(clientIPaddress)) {
			clientIPaddress = request.getRemoteAddr();
			if (lOCAL_HOST_IPV4.equals(clientIPaddress) || lOCAL_HOST_IPV6.equals(clientIPaddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					clientIPaddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					//e.printStackTrace();
					logger.error(e.getMessage(),e);
				}
			}
		}
		if (StringUtils.hasLength(clientIPaddress) && clientIPaddress.length() > 15
				&& clientIPaddress.indexOf(",") > 0) {
			clientIPaddress = clientIPaddress.substring(0, clientIPaddress.indexOf(","));

		}
		return clientIPaddress;
	}
}
