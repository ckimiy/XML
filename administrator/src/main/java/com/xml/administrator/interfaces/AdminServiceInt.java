package com.xml.administrator.interfaces;

import com.xml.administrator.model.Admin;

public interface AdminServiceInt {
	public Admin findUserByEmail(String email);
}
