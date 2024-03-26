package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Gate;


public class GateRepositoryImpl implements GateRepository {

	private Map<Long, Gate> map;

	public GateRepositoryImpl() {
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

