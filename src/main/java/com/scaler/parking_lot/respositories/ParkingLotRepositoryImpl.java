package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.ParkingLot;


public class ParkingLotRepositoryImpl implements ParkingLotRepository {

	private Map<Long, ParkingLot> map;

	public ParkingLotRepositoryImpl() {
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

