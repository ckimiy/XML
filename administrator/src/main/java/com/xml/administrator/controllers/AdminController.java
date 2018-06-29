package com.xml.administrator.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.security.SecurityConstants;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/admin")
public class AdminController {

	
	@RequestMapping(value ="/status", method = RequestMethod.GET)
	public ResponseEntity<String> status(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		String email = Jwts.parser()
        .setSigningKey(SecurityConstants.SECRET.getBytes())
        .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
        .getBody()
        .getSubject();
		return new ResponseEntity<String>(email, HttpStatus.OK);
	}
}
