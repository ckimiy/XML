package com.xml.administrator.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.dto.AgentDTO;
import com.xml.administrator.model.Admin;
import com.xml.administrator.model.TUser;
import com.xml.administrator.services.AgentService;
import com.xml.administrator.services.TUserService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	private TUserService tuserSer;
	
	@Autowired
	private AgentService agentSer;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> registerUser(@RequestBody AgentDTO agentDTO, HttpServletRequest request) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity<String>("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		TUser temp = tuserSer.findUserByEmail(agentDTO.getEmail());
		if (temp == null) {
			
			TUser registerUser = agentSer.save(agentDTO);
			if (registerUser != null) {
				return new ResponseEntity<String>("Registrovan.", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Vec postoji.", HttpStatus.BAD_REQUEST);
	}
}
