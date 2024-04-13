package com.example.models;

import com.example.enums.AdditionalService;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
public class Ticket extends BaseModel{
	
    private Vehicle vehicle;

    private Date entryTime;

    private ParkingSpot parkingSpot;

    private Gate gate;

    private ParkingAttendant parkingAttendant;

    private List<AdditionalService> additionalServices;

}
