package com.example.models;

import com.example.enums.GateType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gate extends BaseModel {

    private String name;

    private GateType type;

    private ParkingAttendant parkingAttendant;

}
