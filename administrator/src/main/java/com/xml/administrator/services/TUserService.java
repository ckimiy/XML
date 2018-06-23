package com.xml.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.interfaces.TUserServiceInt;
import com.xml.administrator.model.TUser;
import com.xml.administrator.repositories.TUserRepository;

@Service
public class TUserService implements TUserServiceInt{

	@Autowired
	private TUserRepository tuserRep;
	
	@Override
	public TUser findUserByEmail(String email) {
		TUser tuser = tuserRep.findByEmail(email);
		return tuser;
	}

}
