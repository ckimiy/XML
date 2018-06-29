package com.xml.administrator.controllers;

import java.util.ArrayList;

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
import com.xml.administrator.services.CodebookService;

@RestController
@RequestMapping("/codebook")
public class CodebookController {
	
	@Autowired
	private CodebookService codeSer;
	
	
	// ----- Object Category
	@RequestMapping(value ="/objCategory/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:code')")
	public ResponseEntity<ArrayList<ObjectCategory>> getObjCategories() {
		
		return ResponseEntity.status(200).body(codeSer.getAllObjCategories());
	}
	
	@RequestMapping(value = "/objCategory/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('add:code')")
	public ResponseEntity<ObjectCategory> addObjCategory(@RequestBody ContentDTO content) throws Exception {
		
		ObjectCategory temp = codeSer.addObjCate(content);
		if (temp != null) {
			return new ResponseEntity<ObjectCategory>(temp, HttpStatus.CREATED);
		}
		return new ResponseEntity<ObjectCategory>(temp, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/objCategory/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('edit:code')")
	public ResponseEntity<ObjectCategory> editObjCategory(@PathVariable  Long id, @RequestBody ContentDTO content) throws Exception {
		ObjectCategory temp = codeSer.editObjCate(id, content.getContent());
		if (temp == null) {
			return new ResponseEntity<ObjectCategory>(temp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ObjectCategory>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/objCategory/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:code')")
	public ResponseEntity<String> deleteObjCategory(@PathVariable  Long id) throws Exception {
		
		ObjectCategory temp = codeSer.deleteObjCate(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
	}
	
	// ---- Object type
	
	@RequestMapping(value ="/objType/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:code')")
	public ResponseEntity<ArrayList<ObjectType>> getObjTypes() {
		
		return ResponseEntity.status(200).body(codeSer.getAllObjTypes());
	}
	
	@RequestMapping(value = "/objType/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('add:code')")
	public ResponseEntity<ObjectType> addObjType(@RequestBody ContentDTO content) throws Exception {
		
		ObjectType temp = codeSer.addObjType(content);
		if (temp != null) {
			return new ResponseEntity<ObjectType>(temp, HttpStatus.CREATED);
		}
		return new ResponseEntity<ObjectType>(temp, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/objType/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('edit:code')")
	public ResponseEntity<ObjectType> editObjType(@PathVariable  Long id, @RequestBody ContentDTO content) throws Exception {
		
		ObjectType temp = codeSer.editObjType(id, content.getContent());
		if (temp == null) {
			return new ResponseEntity<ObjectType>(temp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ObjectType>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/objType/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:code')")
	public ResponseEntity<String> deleteObjType(@PathVariable  Long id) throws Exception {
		
		ObjectType temp = codeSer.deleteObjType(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
	}
	
	// ---- Services
	
	@RequestMapping(value ="/services/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:code')")
	public ResponseEntity<ArrayList<Services>> getServices() {
		
		return ResponseEntity.status(200).body(codeSer.getAllServices());
	}
	
	@RequestMapping(value = "/services/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('add:code')")
	public ResponseEntity<Services> addServices(@RequestBody ContentDTO content) throws Exception {
		Services temp = codeSer.addServ(content);
		if (temp != null) {
			return new ResponseEntity<Services>(temp, HttpStatus.CREATED);
		}
		return new ResponseEntity<Services>(temp, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/services/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('edit:code')")
	public ResponseEntity<Services> editServices(@PathVariable  Long id, @RequestBody ContentDTO content) throws Exception {
		Services temp = codeSer.editServices(id, content.getContent());
		if (temp == null) {
			return new ResponseEntity<Services>(temp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Services>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('delete:code')")
	public ResponseEntity<String> deleteServices(@PathVariable  Long id) throws Exception {
		Services temp = codeSer.deleteServices(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije izbrisano.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Izbrisano.", HttpStatus.OK);
	}
}
