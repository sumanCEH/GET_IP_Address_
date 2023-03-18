package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

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

	
	    @GetMapping("/router-mac-address")
	    public String getRouterMacAddress(HttpServletRequest request) throws UnknownHostException, SocketException {
	        
	    	String  clientIPaddress = requestService.getClientIPAddress(request);
	    	
	        // Get the IP address of the router
	        InetAddress address = InetAddress.getByName(clientIPaddress); // Replace with your router's IP address
	        
	        // Get the MAC address of the router
	        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
	        byte[] mac = ni.getHardwareAddress();
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < mac.length; i++) {
	            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	        }
	        
	        return sb.toString();
	    }
	
}
