package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
