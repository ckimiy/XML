package com.xml.administrator.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xml.administrator.interfaces.AdminServiceInt;
import com.xml.administrator.model.Admin;
import com.xml.administrator.repositories.AdminRepository;
@Service
public class AdminService implements AdminServiceInt, UserDetailsService  {
	
	@Autowired
	private AdminRepository adminRep;
	
	@Override
	public Admin findUserByEmail(String email) {
		Admin temp = adminRep.findByEmail(email);
		return temp;
	}

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		Admin temp = adminRep.findByEmail(arg0);
		System.out.println("temp: "+temp);
		if(temp == null) return null;
		else {
			Set authorities = new HashSet<>();
			temp.getPermissions().forEach(permission -> {
	            authorities.add(new SimpleGrantedAuthority(permission.getName()));
			});
			return new User(temp.getEmail(), temp.getPassword(), authorities);
		}
	}

}
