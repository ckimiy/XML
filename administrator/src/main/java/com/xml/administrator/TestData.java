package com.xml.administrator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.xml.administrator.model.Admin;
import com.xml.administrator.repositories.AdminRepository;
import com.xml.administrator.services.PermissionService;


public class TestData {

	@Autowired
	private PermissionService perSer;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	private void init() {
		

	}
}
