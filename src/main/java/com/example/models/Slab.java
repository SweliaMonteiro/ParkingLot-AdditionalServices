package com.example.models;

import com.example.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slab extends BaseModel{

    private int startHour;

    private int endHour;

    private double price;

    private VehicleType vehicleType;

}
