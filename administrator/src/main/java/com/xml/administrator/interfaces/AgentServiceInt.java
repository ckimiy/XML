package com.xml.administrator.interfaces;

import java.util.ArrayList;

import com.xml.administrator.dto.AgentDTO;
import com.xml.administrator.model.Agent;

public interface AgentServiceInt {
	
	public ArrayList<Agent>	getAllUsers();
	public Agent save(AgentDTO agentDTO);
	public Agent findUserByEmail(String email);
}
