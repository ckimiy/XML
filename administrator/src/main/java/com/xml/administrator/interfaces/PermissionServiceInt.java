package com.xml.administrator.interfaces;

import com.xml.administrator.model.Permission;
import com.xml.administrator.model.TUser;

public interface PermissionServiceInt {
	
	public Permission addPermission(String permissionName);
	public Permission addTUserPermission(TUser user, String permissionName);
	
}
