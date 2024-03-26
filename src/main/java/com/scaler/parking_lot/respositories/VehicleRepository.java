package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Vehicle;

import java.util.Optional;


public interface VehicleRepository {

	public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber);

	public Vehicle save(Vehicle vehicle);

}
