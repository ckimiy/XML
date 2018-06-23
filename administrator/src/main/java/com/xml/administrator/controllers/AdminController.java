package com.xml.administrator.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value ="/status", method = RequestMethod.GET)
	public ResponseEntity status() {
		return ResponseEntity.status(200).body("Ulogovan");
	}
}
