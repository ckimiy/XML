package com.xml.administrator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.administrator.model.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

	Agent findByEmail(String email);
}
