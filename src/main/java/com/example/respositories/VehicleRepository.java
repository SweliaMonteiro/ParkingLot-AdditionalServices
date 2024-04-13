package com.example.respositories;

import java.util.*;
import com.example.models.Vehicle;
import org.springframework.stereotype.Repository;


@Repository
public class VehicleRepository {

	private Map<Long, Vehicle> map;

	public VehicleRepository() {
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
