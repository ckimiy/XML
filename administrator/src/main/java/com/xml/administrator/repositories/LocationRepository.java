package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
