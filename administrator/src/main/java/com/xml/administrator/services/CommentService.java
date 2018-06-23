package com.xml.administrator.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.dto.CommentDTO;
import com.xml.administrator.interfaces.CommentServiceInt;
import com.xml.administrator.model.Comment;
import com.xml.administrator.repositories.AccomodationRepository;
import com.xml.administrator.repositories.CommentRepository;

@Service
public class CommentService implements CommentServiceInt {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private AccomodationRepository accRepository;

	@Override
	public ArrayList<CommentDTO> getAllDisapprovedComments() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Comment> listEntities = (ArrayList<Comment>) commentRepository.findAll();
		ArrayList<CommentDTO> listDTO = new ArrayList<CommentDTO>();

		for (Comment tmp : listEntities) {
			try {
				if(!tmp.isApproved()) {
					CommentDTO dto = mapper.map(tmp, CommentDTO.class);
					listDTO.add(dto);
				}
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public Comment save(Long id, boolean approved) {
		Comment temp = commentRepository.findById(id).get();
		if(temp != null) {
			temp.setApproved(approved);
			commentRepository.save(temp);
			return temp;
		}
		return null;
	}

	@Override
	public Comment delete(Long id) {
		Comment temp = commentRepository.findById(id).get();
		if(temp != null) {
			temp.getAccomodation().getComment().remove(temp);
			accRepository.save(temp.getAccomodation());
			commentRepository.delete(temp);
			return temp;
		}
		return null;
	}

}
