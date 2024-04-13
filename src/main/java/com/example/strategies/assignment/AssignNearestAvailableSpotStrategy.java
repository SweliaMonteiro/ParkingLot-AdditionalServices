package com.example.strategies.assignment;

import com.example.enums.*;
import com.example.models.*;
import java.util.*;


public class AssignNearestAvailableSpotStrategy implements SpotAssignmentStrategy {

	@Override
	public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
		// Loop through all the floors of the parking lot
		for (ParkingFloor parkingFloor : parkingLot.getParkingFloors()) {
			// If the floor is operational then go forward
			if(parkingFloor.getStatus().equals(FloorStatus.OPERATIONAL)){
				// Loop through all the spots of the operational floor
				for (ParkingSpot spot : parkingFloor.getSpots()) {
					// If the spot is available and if it allows the given type of vehicle then mark it as occupied and return the spot
					if(spot.getStatus().equals(ParkingSpotStatus.AVAILABLE) && spot.getSupportedVehicleType().equals(vehicleType)){
						spot.setStatus(ParkingSpotStatus.OCCUPIED);
						return Optional.of(spot);
					}
				}
			}
		}
		// If no spot found then return null
		return Optional.empty();
	}

}
