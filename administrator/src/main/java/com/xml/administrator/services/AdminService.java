package com.xml.administrator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.interfaces.AdminServiceInt;
import com.xml.administrator.model.Admin;
import com.xml.administrator.repositories.AdminRepository;

@Service
public class AdminService implements AdminServiceInt {
	
	@Autowired
	private AdminRepository adminRep;
	
	@Override
	public Admin findUserByEmail(String email) {
		Admin temp = adminRep.findByEmail(email);
		return temp;
	}

}
