package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Gate;

import java.util.Optional;


public interface GateRepository {

	public Optional<Gate> findById(long gateId);

	public Gate save(Gate gate);

}
