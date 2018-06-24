package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByEmail(String email);
}
