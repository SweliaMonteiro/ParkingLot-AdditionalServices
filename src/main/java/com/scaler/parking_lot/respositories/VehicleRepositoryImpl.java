package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Vehicle;


public class VehicleRepositoryImpl implements VehicleRepository {

	private Map<Long, Vehicle> map;

	public VehicleRepositoryImpl() {
		map = new HashMap<Long, Vehicle>();
	}	

	public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
		for(Vehicle vehicle:map.values()) {
			if(vehicle.getRegistrationNumber() == registrationNumber) {
				return Optional.ofNullable(vehicle);
			}
		}
		return Optional.empty();
	}

	public Vehicle save(Vehicle vehicle) {
		if(vehicle.getId()==0) {
			vehicle.setId(map.size()+1);
		}
		map.put(vehicle.getId(), vehicle);
		return vehicle;
	}
}

