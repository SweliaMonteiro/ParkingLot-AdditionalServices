package com.scaler.parking_lot.respositories;

import java.util.Optional;

import com.scaler.parking_lot.models.ParkingLot;


public interface ParkingLotRepository {

    public Optional<ParkingLot> getParkingLotByGateId(long gateId);

    public Optional<ParkingLot> getParkingLotById(long id);

    public ParkingLot save(ParkingLot parkingLot);
    
}
