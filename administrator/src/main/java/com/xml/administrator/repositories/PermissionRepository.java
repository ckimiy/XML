package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Permission findByName(String name);
}
