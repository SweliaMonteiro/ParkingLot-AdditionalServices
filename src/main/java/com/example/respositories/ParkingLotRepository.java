package com.example.respositories;

import java.util.*;
import com.example.models.*;
import org.springframework.stereotype.Repository;


@Repository
public class ParkingLotRepository {

	private Map<Long, ParkingLot> map;

	public ParkingLotRepository() {
		map = new HashMap<Long, ParkingLot>();
	}

	public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
		for(ParkingLot parkingLot:map.values()) {
			List<Gate> gates = parkingLot.getGates(); 
			for(Gate gate:gates) {
				if(gate.getId() == gateId) {
					return Optional.ofNullable(parkingLot);
				}
			}
		}
		return Optional.empty();
	}

	public Optional<ParkingLot> getParkingLotById(long id) {
		return Optional.ofNullable(map.get(id));
	}

	public ParkingLot save(ParkingLot parkingLot) {
		if(parkingLot.getId()==0) {
			parkingLot.setId(map.size()+1);
		}
		map.put(parkingLot.getId(), parkingLot);
		return parkingLot;
	}
}
