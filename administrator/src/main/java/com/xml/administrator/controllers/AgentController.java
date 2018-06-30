package com.xml.administrator.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.dto.AgentDTO;
import com.xml.administrator.model.TUser;
import com.xml.administrator.security.JWTTokenUtil;
import com.xml.administrator.security.SecurityConstants;
import com.xml.administrator.services.AgentService;
import com.xml.administrator.services.TUserService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	private static Logger logger = LogManager.getLogger(AgentController.class);
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	
	@Autowired
	private TUserService tuserSer;
	
	@Autowired
	private AgentService agentSer;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('add:agent')")
	public ResponseEntity<String> registerUser(@RequestBody AgentDTO agentDTO, HttpServletRequest request) throws Exception {
		TUser temp = tuserSer.findUserByEmail(agentDTO.getEmail());
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		if (temp == null) {
			
			TUser registerUser = agentSer.save(agentDTO);
			if (registerUser != null) {
				logger.info("Administrator sa mail-om "
						+ email + " se registrovao na sistem.");
				return new ResponseEntity<String>("Registrovan.", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<String>("Vec postoji.", HttpStatus.BAD_REQUEST);
	}
}
