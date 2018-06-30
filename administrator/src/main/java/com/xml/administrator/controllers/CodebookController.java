package com.xml.administrator.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.dto.ContentDTO;
import com.xml.administrator.model.ObjectCategory;
import com.xml.administrator.model.ObjectType;
import com.xml.administrator.model.Services;
import com.xml.administrator.security.JWTTokenUtil;
import com.xml.administrator.security.SecurityConstants;
import com.xml.administrator.services.CodebookService;

@RestController
@RequestMapping("/codebook")
public class CodebookController {
	
	private static Logger logger = LogManager.getLogger(CodebookController.class);
	
	@Autowired
	private CodebookService codeSer;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	// ----- Object Category
	@RequestMapping(value ="/objCategory/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:code')")
	public ResponseEntity<ArrayList<ObjectCategory>> getObjCategories() {
		
		return ResponseEntity.status(200).body(codeSer.getAllObjCategories());
	}
	
	@RequestMapping(value = "/objCategory/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('add:code')")
	public ResponseEntity<ObjectCategory> addObjCategory(@RequestBody ContentDTO content,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		ObjectCategory temp = codeSer.addObjCate(content);
		if (temp != null) {
			logger.info("Administrator sa mail-om " + email +
					" je dodao kategoriju " + temp.getCategory() + " sa id = " +
					temp.getId());
			return new ResponseEntity<ObjectCategory>(temp, HttpStatus.CREATED);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da doda kategoriju.");
		return new ResponseEntity<ObjectCategory>(temp, HttpStatus.BAD_REQUEST);
	}
	}
	
	@RequestMapping(value = "/objCategory/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('edit:code')")
	public ResponseEntity<ObjectCategory> editObjCategory(@PathVariable  Long id, @RequestBody ContentDTO content,HttpServletRequest request) throws Exception {
		ObjectCategory temp = codeSer.editObjCate(id, content.getContent());
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		if (temp == null) {
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da izmijeni kategoriju.");
			return new ResponseEntity<ObjectCategory>(temp, HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je izmijenio kategoriju " + temp.getCategory() + " sa id = " +
					temp.getId());
		return new ResponseEntity<ObjectCategory>(temp, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/objCategory/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:code')")
	public ResponseEntity<String> deleteObjCategory(@PathVariable  Long id,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		ObjectCategory temp = codeSer.deleteObjCate(id);
		if (temp == null) {
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da obrise kategoriju.");
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je izbrisao kategoriju " + temp.getCategory() + " sa id = " +
					temp.getId());
		
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
		}
	}
	
	// ---- Object type
	
	@RequestMapping(value ="/objType/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:code')")
	public ResponseEntity<ArrayList<ObjectType>> getObjTypes() {
		
		return ResponseEntity.status(200).body(codeSer.getAllObjTypes());
	}
	
	@RequestMapping(value = "/objType/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('add:code')")
	public ResponseEntity<ObjectType> addObjType(@RequestBody ContentDTO content,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		ObjectType temp = codeSer.addObjType(content);
		if (temp != null) {
			logger.info("Administrator sa mail-om " + email +
					" je dodao tip " + temp.getType() + " sa id = " + temp.getId());
			return new ResponseEntity<ObjectType>(temp, HttpStatus.CREATED);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da doda tip.");
		return new ResponseEntity<ObjectType>(temp, HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value = "/objType/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('edit:code')")
	public ResponseEntity<ObjectType> editObjType(@PathVariable  Long id, @RequestBody ContentDTO content,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		ObjectType temp = codeSer.editObjType(id, content.getContent());
		if (temp == null) {
			
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da izmijeni tip.");
			return new ResponseEntity<ObjectType>(temp, HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je izmijenio tip " + temp.getType() + " sa id = " +
					temp.getId());
		return new ResponseEntity<ObjectType>(temp, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/objType/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:code')")
	public ResponseEntity<String> deleteObjType(@PathVariable  Long id,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		ObjectType temp = codeSer.deleteObjType(id);
		if (temp == null) {
			logger.info("Administrator sa mail-om " + email +
				" je pokusao da obrise tip.");
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je izbrisao tip " + temp.getType() + " sa id = " +
					temp.getId());
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
		}
	}
	
	// ---- Services
	
	@RequestMapping(value ="/services/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:code')")
	public ResponseEntity<ArrayList<Services>> getServices() {
		
		return ResponseEntity.status(200).body(codeSer.getAllServices());
	}
	
	@RequestMapping(value = "/services/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('add:code')")
	public ResponseEntity<Services> addServices(@RequestBody ContentDTO content,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		Services temp = codeSer.addServ(content);
		if (temp != null) {
			logger.info("Administrator sa mail-om " + email +
					" je dodao servis " + temp.getService() + " sa id = " +
					temp.getId());
			return new ResponseEntity<Services>(temp, HttpStatus.CREATED);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da doda servis.");
			return new ResponseEntity<Services>(temp, HttpStatus.BAD_REQUEST);
		}
	}
	@RequestMapping(value = "/services/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('edit:code')")
	public ResponseEntity<Services> editServices(@PathVariable  Long id, @RequestBody ContentDTO content,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		Services temp = codeSer.editServices(id, content.getContent());
		if (temp == null) {
			logger.info("Administrator sa mail-om " + email +
					" je pokusao da izmijeni servis.");
			return new ResponseEntity<Services>(temp, HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je izmijenio servis " + temp.getService() + " sa id = " +
					temp.getId());
		return new ResponseEntity<Services>(temp, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:code')")
	public ResponseEntity<String> deleteServices(@PathVariable  Long id,HttpServletRequest request) throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING).substring((SecurityConstants.TOKEN_PREFIX).length());
        String email = jwtTokenUtil.getEmailFromToken(token);
		Services temp = codeSer.deleteServices(id);
		if (temp == null) {

			logger.info("Administrator sa mail-om " + email +
					" je pokusao da izbrise servis.");
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}else {
			logger.info("Administrator sa mail-om " + email +
					" je izbrisao servis " + temp.getService() + " sa id = " +
					temp.getId());
			return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
		}
	}
}