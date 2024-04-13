package com.example.respositories;

import java.util.*;
import com.example.models.Gate;
import org.springframework.stereotype.Repository;


@Repository
public class GateRepository {

	private Map<Long, Gate> map;

	public GateRepository() {
		map = new HashMap<Long, Gate>();
	}

	public Optional<Gate> findById(long gateId) {
		return Optional.ofNullable(map.get(gateId));
	}

	public Gate save(Gate gate) {
		if(gate.getId()==0) {
			gate.setId(map.size()+1);
		}
		map.put(gate.getId(), gate);
		return gate;
	}
}
