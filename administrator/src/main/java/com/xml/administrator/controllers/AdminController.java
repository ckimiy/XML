package com.xml.administrator.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.dto.AdminLoginDTO;
import com.xml.administrator.model.Admin;
import com.xml.administrator.model.TUser;
import com.xml.administrator.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminSer;
	
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping(value ="/status", method = RequestMethod.GET)
	public ResponseEntity<String> status(HttpServletRequest request) {
		TUser temp = (Admin)request.getSession().getAttribute("admin");
		if (temp != null) {
			return new ResponseEntity<String>(temp.getEmail(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Odjavljen.", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody AdminLoginDTO adminDTO) {
		TUser temp = adminSer.findUserByEmail(adminDTO.getEmail());
		System.out.println(adminDTO.getPassword()+", "+adminDTO.getEmail());
		if (temp != null) {
			System.out.println(adminDTO.getPassword()+", "+temp.getPassword());
			if (temp.getPassword().equals(adminDTO.getPassword())) {
				System.out.println("ssss");
				request.getSession().setAttribute("admin", temp);
				return new ResponseEntity<String>("Ulogovan.", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("Greska pri logovanju.", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<String> logout() {
		TUser temp = (Admin)request.getSession().getAttribute("admin");
		if (temp != null) {
			request.getSession().removeAttribute("admin");
			return new ResponseEntity<String>("Odjavljen.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Greska pri odjavljivanju.", HttpStatus.BAD_REQUEST);
	}
}
