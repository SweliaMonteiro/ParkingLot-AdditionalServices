package com.example.strategies.assignment;

import com.example.models.*;
import com.example.enums.VehicleType;
import java.util.Optional;


public interface SpotAssignmentStrategy {

    Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType);

}
