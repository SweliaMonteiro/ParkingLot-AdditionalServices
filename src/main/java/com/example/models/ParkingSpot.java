package com.example.models;

import com.example.enums.ParkingSpotStatus;
import com.example.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpot extends BaseModel{

    private int number;

    private VehicleType supportedVehicleType;

    private ParkingSpotStatus status;

}
