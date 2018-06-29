package com.xml.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.administrator.model.Comment;
import com.xml.administrator.services.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentSer;
	

	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('get:comment')")
	public ResponseEntity getAllComments() {
		
		return ResponseEntity.status(200).body(commentSer.getAllDisapprovedComments());
	}
	
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('approve:comment')")
	public ResponseEntity<String> approveComment(@PathVariable  Long id) throws Exception {
		
		Comment temp = commentSer.save(id, true);
		if (temp == null) {
			return new ResponseEntity<String>("Nije prihvaceno.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Prihvaceno.", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/disapprove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('disapprove:comment')")
	public ResponseEntity<String> disapproveComment(@PathVariable  Long id) throws Exception {
		
		Comment temp = commentSer.delete(id);
		if (temp == null) {
			return new ResponseEntity<String>("Nije prihvaceno.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Prihvaceno.", HttpStatus.OK);
	}
}
