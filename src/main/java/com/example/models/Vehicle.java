package com.example.models;

import com.example.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle extends BaseModel{

    private String registrationNumber;

    private VehicleType vehicleType;

}
