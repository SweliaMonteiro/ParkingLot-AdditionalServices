package com.scaler.parking_lot.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.scaler.parking_lot.exceptions.AdditionalServiceNotSupportedByVehicle;
import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.exceptions.ParkingSpotNotAvailableException;
import com.scaler.parking_lot.exceptions.UnsupportedAdditionalService;
import com.scaler.parking_lot.models.AdditionalService;
import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.GateType;
import com.scaler.parking_lot.models.ParkingLot;
import com.scaler.parking_lot.models.ParkingSpot;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.models.Vehicle;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.ParkingLotRepository;
import com.scaler.parking_lot.respositories.TicketRepository;
import com.scaler.parking_lot.respositories.VehicleRepository;
import com.scaler.parking_lot.strategies.assignment.SpotAssignmentStrategy;


public class TicketServiceImpl implements TicketService{
    
	private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;
    
    public TicketServiceImpl(GateRepository gateRepository, VehicleRepository vehicleRepository, SpotAssignmentStrategy spotAssignmentStrategy, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    
    @Override
    public Ticket generateTicket(long gateId, String registrationNumber, String vehicleType, List<String> additionalServicesList) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException, UnsupportedAdditionalService, AdditionalServiceNotSupportedByVehicle {
        // Get the Gate from the DB with the given gateId
    	Optional<Gate> optionalGate = this.gateRepository.findById(gateId);
        if (optionalGate.isEmpty()) {
            throw new InvalidGateException("Invalid gate id");
        }
        // If vehicle is trying to enter from exit gate then throw error
        Gate gate = optionalGate.get();
        if(gate.getType().equals(GateType.EXIT)) {
            throw new InvalidGateException("Vehicle trying to enter from exit gate");
        }
        
        // Get the Vehicle from the DB with given registrationNumber, if Vehicle not present then create new Vehicle object and store in DB 
        Vehicle vehicle;
        Optional<Vehicle> optionalVehicle = vehicleRepository.getVehicleByRegistrationNumber(registrationNumber);
        if (optionalVehicle.isEmpty()) {
            vehicle = new Vehicle();
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setVehicleType(VehicleType.valueOf(vehicleType));
            vehicle = vehicleRepository.save(vehicle);
        } 
        else {
            vehicle = optionalVehicle.get();
        }
        
        // Get the ParkingLot from the DB with the given gateId
        Optional<ParkingLot> parkingLotOptional = this.parkingLotRepository.getParkingLotByGateId(gateId);
        if (parkingLotOptional.isEmpty()) {
            throw new InvalidParkingLotException("Invalid parking lot id");
        }
        ParkingLot parkingLot = parkingLotOptional.get();
        
        // Assign a spot for the Vehicle using SpotAssignmentStrategy, if no free spots available then throw error
        Optional<ParkingSpot> parkingSpotOptional = spotAssignmentStrategy.assignSpot(parkingLot, VehicleType.valueOf(vehicleType));
        if (parkingSpotOptional.isEmpty()) {
            throw new ParkingSpotNotAvailableException("No parking spot available");
        }
        ParkingSpot parkingSpot = parkingSpotOptional.get();
        
        // Create a AdditionalService list to add in the Ticket
        List<AdditionalService> additionalServices = new ArrayList<>(); 
        if(additionalServicesList != null) {       
            for(String additionalService:additionalServicesList) {
        	    AdditionalService actualAdditionalService;
        	    // If the additional service provided in the input is not valid AdditionalService, then throw error
        	    try {
        		    actualAdditionalService = AdditionalService.valueOf(additionalService);
        	    }
        	    catch(IllegalArgumentException e) {
        		    throw new UnsupportedAdditionalService("Unsupported additional service");
        	    }
        	    // If the additional service provided in the input doesn't support the given vehicle type then throw error
        	    if(!actualAdditionalService.getSupportedVehicleTypes().contains(VehicleType.valueOf(vehicleType))) {
        		    throw new AdditionalServiceNotSupportedByVehicle("Additional service not supported by vehicle");
        	    }	
        	    additionalServices.add(actualAdditionalService);
            }
        }
        
        // Create a ticket with the required details and save in the DB
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());
        ticket.setParkingSpot(parkingSpot);
        ticket.setGate(gate);
        ticket.setParkingAttendant(gate.getParkingAttendant());
        ticket.setAdditionalServices(additionalServices);
 
        return this.ticketRepository.save(ticket);
    }

}
