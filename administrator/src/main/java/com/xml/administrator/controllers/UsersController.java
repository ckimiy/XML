package com.xml.administrator.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.dto.UserSimpleDTO;
import com.xml.administrator.model.Admin;
import com.xml.administrator.model.TUser;
import com.xml.administrator.model.User;
import com.xml.administrator.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired 
	private UsersService usersSer;
	
	@Autowired
	private HttpServletRequest request;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('get:users')")
	public ResponseEntity getAllUsers() {
		
		return ResponseEntity.status(200).body(usersSer.getAllUsers());
	}
	
	@RequestMapping(value = "/activate/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('update:users')")
	public ResponseEntity<UserSimpleDTO> activateUser(@PathVariable  Long id) throws Exception {
		
		UserSimpleDTO temp = usersSer.activate(id);
		if (temp != null) {
			return new ResponseEntity<UserSimpleDTO>(temp, HttpStatus.OK);
		}
		return new ResponseEntity<UserSimpleDTO>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/block/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('update:users')")
	public ResponseEntity<UserSimpleDTO> blockUser(@PathVariable  Long id) throws Exception {
		
		UserSimpleDTO temp = usersSer.block(id);
		if (temp != null) {
			return new ResponseEntity<UserSimpleDTO>(temp, HttpStatus.OK);
		}
		return new ResponseEntity<UserSimpleDTO>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:users')")
	public ResponseEntity<String> deleteObjCategory(@PathVariable  Long id) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity<String>("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		User temp = usersSer.delete(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisan.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisan.", HttpStatus.OK);
	}
}
