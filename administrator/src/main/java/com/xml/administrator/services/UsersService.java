package com.xml.administrator.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.dto.UserSimpleDTO;
import com.xml.administrator.interfaces.UsersServiceInt;
import com.xml.administrator.model.User;
import com.xml.administrator.repositories.UserRepository;

@Service
public class UsersService implements UsersServiceInt {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ArrayList<UserSimpleDTO> getAllUsers() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = (ArrayList<User>) userRepository.findAll();
		ArrayList<UserSimpleDTO> listDTO = new ArrayList<UserSimpleDTO>();

		for (User tmp : listEntities) {
			try {
				UserSimpleDTO dto = mapper.map(tmp, UserSimpleDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public User save(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public UserSimpleDTO activate(Long id) {
		User temp = userRepository.findById(id).get();
		if(temp == null) return null;
		temp.setBlocked(false);
		userRepository.save(temp);
		
		ModelMapper mapper = new ModelMapper();
		UserSimpleDTO dto;
		try {
			dto = mapper.map(temp, UserSimpleDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
		
	}
	
	@Override
	public UserSimpleDTO block(Long id) {
		User temp = userRepository.findById(id).get();
		if(temp == null) return null;
		temp.setBlocked(true);
		userRepository.save(temp);

		ModelMapper mapper = new ModelMapper();
		UserSimpleDTO dto;
		try {
			dto = mapper.map(temp, UserSimpleDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return dto;
	}

	@Override
	public User delete(Long id) {
		User temp = userRepository.findById(id).get();
		if(temp == null) return null;
		userRepository.delete(temp);
		return temp;
	}

}
