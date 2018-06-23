package com.xml.administrator.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.administrator.dto.AgentDTO;
import com.xml.administrator.dto.UserSimpleDTO;
import com.xml.administrator.interfaces.AgentServiceInt;
import com.xml.administrator.model.Agent;
import com.xml.administrator.model.Location;
import com.xml.administrator.repositories.AgentRepository;
import com.xml.administrator.repositories.LocationRepository;

@Service
public class AgentService implements AgentServiceInt {
	
	@Autowired
	private AgentRepository agentRep;
	
	@Autowired
	private LocationRepository locRep;

	@Override
	public Agent save(AgentDTO agentDTO) {
		Agent agent = new Agent();
		Location loc = new Location();
		
		ModelMapper mapper = new ModelMapper();
		try {
			loc = mapper.map(agentDTO, Location.class);
			agent = mapper.map(agentDTO, Agent.class);
			
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		
		locRep.save(loc);
		agent.setLocation(loc);
		agentRep.save(agent);
		return agent;
	}

	@Override
	public Agent findUserByEmail(String email) {
		Agent agent= agentRep.findByEmail(email);
		return agent;
	}

	@Override
	public ArrayList<Agent> getAllUsers() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Agent> listEntities = (ArrayList<Agent>) agentRep.findAll();
		ArrayList<UserSimpleDTO> listDTO = new ArrayList<UserSimpleDTO>();

		for (Agent tmp : listEntities) {
			System.out.println(tmp.getEmail());
		}

		return listEntities;
	}
}
