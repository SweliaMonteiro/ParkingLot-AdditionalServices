package com.example.models;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Invoice extends BaseModel{
    
    private Ticket ticket;

    private Gate gate;

    private ParkingAttendant parkingAttendant;

    private Date exitTime;

    private double amount;

}
