package com.xml.administrator.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.dto.ContentDTO;
import com.xml.administrator.model.Admin;
import com.xml.administrator.model.ObjectCategory;
import com.xml.administrator.model.ObjectType;
import com.xml.administrator.model.Services;
import com.xml.administrator.model.TUser;
import com.xml.administrator.services.CodebookService;

@RestController
@RequestMapping("/codebook")
public class CodebookController {
	
	@Autowired
	private CodebookService codeSer;
	
	@Autowired
	private HttpServletRequest request;
	
	// ----- Object Category
	@RequestMapping(value ="/objCategory/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ObjectCategory>> getObjCategories() {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.status(200).body(codeSer.getAllObjCategories());
	}
	
	@RequestMapping(value = "/objCategory/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectCategory> addObjCategory(@RequestBody ContentDTO content) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		ObjectCategory temp = codeSer.addObjCate(content);
		if (temp != null) {
			return new ResponseEntity<ObjectCategory>(temp, HttpStatus.CREATED);
		}
		return new ResponseEntity<ObjectCategory>(temp, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/objCategory/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectCategory> editObjCategory(@PathVariable  Long id, @RequestBody ContentDTO content) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		ObjectCategory temp = codeSer.editObjCate(id, content.getContent());
		if (temp == null) {
			return new ResponseEntity<ObjectCategory>(temp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ObjectCategory>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/objCategory/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteObjCategory(@PathVariable  Long id) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity<String>("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		ObjectCategory temp = codeSer.deleteObjCate(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
	}
	
	// ---- Object type
	
	@RequestMapping(value ="/objType/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<ObjectType>> getObjTypes() {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.status(200).body(codeSer.getAllObjTypes());
	}
	
	@RequestMapping(value = "/objType/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectType> addObjType(@RequestBody ContentDTO content) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		ObjectType temp = codeSer.addObjType(content);
		if (temp != null) {
			return new ResponseEntity<ObjectType>(temp, HttpStatus.CREATED);
		}
		return new ResponseEntity<ObjectType>(temp, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/objType/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ObjectType> editObjType(@PathVariable  Long id, @RequestBody ContentDTO content) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		ObjectType temp = codeSer.editObjType(id, content.getContent());
		if (temp == null) {
			return new ResponseEntity<ObjectType>(temp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ObjectType>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/objType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteObjType(@PathVariable  Long id) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity<String>("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		ObjectType temp = codeSer.deleteObjType(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
	}
	
	// ---- Services
	
	@RequestMapping(value ="/services/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Services>> getServices() {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		return ResponseEntity.status(200).body(codeSer.getAllServices());
	}
	
	@RequestMapping(value = "/services/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Services> addServices(@RequestBody ContentDTO content) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		Services temp = codeSer.addServ(content);
		if (temp != null) {
			return new ResponseEntity<Services>(temp, HttpStatus.CREATED);
		}
		return new ResponseEntity<Services>(temp, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/services/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Services> editServices(@PathVariable  Long id, @RequestBody ContentDTO content) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		Services temp = codeSer.editServices(id, content.getContent());
		if (temp == null) {
			return new ResponseEntity<Services>(temp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Services>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteServices(@PathVariable  Long id) throws Exception {
		TUser tempAdmin = (Admin)request.getSession().getAttribute("admin");
		if(tempAdmin == null) {
			return new ResponseEntity<String>("Niste ulogovani.", HttpStatus.UNAUTHORIZED);
		}
		
		Services temp = codeSer.deleteServices(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
	}
}
