package com.example.services;

import java.util.*;
import com.example.exceptions.*;
import com.example.respositories.*;
import com.example.strategies.pricing.PricingStrategyFactory;
import com.example.enums.*;
import com.example.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private GateRepository gateRepository;

	@Autowired
	private PricingStrategyFactory pricingStrategyFactory;

	@Autowired
	private InvoiceRepository invoiceRepository;


	public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException {
		// Get the Ticket from the DB with the given ticketId
		Optional<Ticket> optionalTicket = ticketRepository.getTicketById(ticketId);
		if(optionalTicket.isEmpty()) {
			throw new TicketNotFoundException("Invalid Ticket id");
		}
		Ticket ticket = optionalTicket.get();

		// Get the Gate from the DB with the given gateId
		Optional<Gate> optionalGate = gateRepository.findById(gateId);
		if(optionalGate.isEmpty()) {
			throw new InvalidGateException("Invalid Gate id");
		}
		Gate gate = optionalGate.get();

		Date exitTime = new Date();

		// Calculate the amount parking fee
		double amount = pricingStrategyFactory.getPricingStrategy(exitTime).calculateAmount(ticket.getEntryTime(), exitTime, ticket.getVehicle().getVehicleType());

		// Add additional services cost to the total amount
		List<AdditionalService> additionalServices = ticket.getAdditionalServices();
		for(AdditionalService additionalService:additionalServices) {
			amount += additionalService.getCost();
		}

		// Generate a invoice with the required details and save in the DB
		Invoice invoice = new Invoice();
		invoice.setTicket(ticket);
		invoice.setGate(gate);
		invoice.setParkingAttendant(gate.getParkingAttendant());
		invoice.setExitTime(exitTime);
		invoice.setAmount(amount);
		return invoiceRepository.save(invoice);
	}

}

