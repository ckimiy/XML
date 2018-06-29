package com.xml.administrator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.xml.administrator.model.Admin;
import com.xml.administrator.repositories.AdminRepository;
import com.xml.administrator.services.PermissionService;

@Component
public class TestData {

	@Autowired
	private AdminRepository adminRep;
	
	@Autowired
	private PermissionService perSer;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	private void init() {
		String email = "admin@admin.com";
		if (adminRep.findByEmail(email) == null) {
			Admin temp = new Admin();
			temp.setEmail(email);
			temp.setName("admin");
			temp.setSurname("admin");
			temp.setUsername("admin");
			temp.setPassword(bCryptPasswordEncoder.encode("admin"));
			
			perSer.addTUserPermission(temp, "get:users");
			perSer.addTUserPermission(temp, "update:users");
			perSer.addTUserPermission(temp, "delete:users");
			perSer.addTUserPermission(temp, "add:agent");
			perSer.addTUserPermission(temp, "add:code");
			perSer.addTUserPermission(temp, "edit:code");
			perSer.addTUserPermission(temp, "get:code");
			perSer.addTUserPermission(temp, "delete:code");
			perSer.addTUserPermission(temp, "get:comment");
			perSer.addTUserPermission(temp, "approve:comment");
			perSer.addTUserPermission(temp, "disapprove:comment");
			
			adminRep.save(temp);
		}
		
		email = "admin1@admin.com";
		if (adminRep.findByEmail(email) == null) {
			Admin temp = new Admin();
			temp.setEmail(email);
			temp.setName("admin1");
			temp.setSurname("admin1");
			temp.setUsername("admin1");
			temp.setPassword(bCryptPasswordEncoder.encode("admin1"));
			
			//perSer.addTUserPermission(temp, "get:users");
			//perSer.addTUserPermission(temp, "update:users");
			//perSer.addTUserPermission(temp, "delete:users");
			perSer.addTUserPermission(temp, "add:agent");
			//perSer.addTUserPermission(temp, "add:code");
			//perSer.addTUserPermission(temp, "edit:code");
			//perSer.addTUserPermission(temp, "get:code");
			//perSer.addTUserPermission(temp, "delete:code");
			perSer.addTUserPermission(temp, "get:comment");
			perSer.addTUserPermission(temp, "approve:comment");
			perSer.addTUserPermission(temp, "disapprove:comment");
			
			adminRep.save(temp);
		}

	}
}
