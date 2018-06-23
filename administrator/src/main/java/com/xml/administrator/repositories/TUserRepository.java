package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.TUser;


public interface TUserRepository  extends JpaRepository<TUser, Long> {

	TUser findByEmail(String email);
}
