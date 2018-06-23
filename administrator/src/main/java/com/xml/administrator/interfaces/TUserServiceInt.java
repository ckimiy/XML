package com.xml.administrator.interfaces;

import com.xml.administrator.model.TUser;

public interface TUserServiceInt {
	public TUser findUserByEmail(String email);
}
