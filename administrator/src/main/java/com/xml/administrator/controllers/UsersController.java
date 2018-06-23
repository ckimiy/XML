package com.xml.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired 
	private UsersService usersSer;
	
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllUsers() {
		return ResponseEntity.status(200).body(usersSer.getAllUsers());
	}
}
