package com.xml.administrator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.interfaces.PermissionServiceInt;
import com.xml.administrator.model.Permission;
import com.xml.administrator.model.TUser;
import com.xml.administrator.repositories.PermissionRepository;
import com.xml.administrator.repositories.TUserRepository;

@Service
public class PermissionService implements PermissionServiceInt {

	@Autowired
	private PermissionRepository permRep;
	@Autowired
	private TUserRepository tRep;
	
	@Override
	public Permission addPermission(String permissionName) {
		Permission temp = permRep.findByName(permissionName);
		if(temp != null) return temp;
		temp = new Permission();
		temp.setName(permissionName);
		permRep.save(temp);
		return temp;
	}

	@Override
	public Permission addTUserPermission(TUser user, String permissionName) {
		Permission temp = permRep.findByName(permissionName);
		
		if(temp != null) {
			List<TUser> tempList = temp.getTusers();
			if(tempList.contains(user)) {
				return temp;
			} else {
				user.getPermissions().add(temp);
				tempList.add(user);
				
				tRep.save(user);
				permRep.save(temp);
				return temp;
			}
		} else {
			temp = addPermission(permissionName);
			
			user.getPermissions().add(temp);
			temp.getTusers().add(user);
			
			tRep.save(user);
			permRep.save(temp);
		}
		return temp;
	}

}
