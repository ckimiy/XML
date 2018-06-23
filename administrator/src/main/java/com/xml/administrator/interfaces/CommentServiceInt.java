package com.xml.administrator.interfaces;

import java.util.ArrayList;

import com.xml.administrator.dto.CommentDTO;
import com.xml.administrator.model.Comment;

public interface CommentServiceInt {
	
	public ArrayList<CommentDTO> getAllDisapprovedComments();
	public Comment save(Long id, boolean approved);
	public Comment delete(Long id);
}
