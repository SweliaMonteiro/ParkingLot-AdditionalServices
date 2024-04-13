package com.example.models;

import com.example.enums.FloorStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ParkingFloor extends BaseModel{

    private List<ParkingSpot> spots;

    private int floorNumber;

    private FloorStatus status;

}
