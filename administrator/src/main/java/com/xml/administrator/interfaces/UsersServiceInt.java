package com.xml.administrator.interfaces;

import java.util.ArrayList;

import com.xml.administrator.dto.UserSimpleDTO;
import com.xml.administrator.model.User;

public interface UsersServiceInt {

	public ArrayList<UserSimpleDTO>	getAllUsers();
	public User save(User user);
	public User findUserByEmail(String email);
}
