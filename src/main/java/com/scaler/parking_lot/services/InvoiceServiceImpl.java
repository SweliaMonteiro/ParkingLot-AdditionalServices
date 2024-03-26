package com.scaler.parking_lot.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.TicketNotFoundException;
import com.scaler.parking_lot.models.AdditionalService;
import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.Invoice;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.InvoiceRepository;
import com.scaler.parking_lot.respositories.TicketRepository;
import com.scaler.parking_lot.strategies.pricing.PricingStrategyFactory;


public class InvoiceServiceImpl implements InvoiceService {

	private TicketRepository ticketRepository;
	private GateRepository gateRepository;
	private PricingStrategyFactory pricingStrategyFactory;
	private InvoiceRepository invoiceRepository;

	public InvoiceServiceImpl(TicketRepository ticketRepository, GateRepository gateRepository, PricingStrategyFactory pricingStrategyFactory, InvoiceRepository invoiceRepository) {
		this.ticketRepository = ticketRepository;
		this.gateRepository = gateRepository;
		this.pricingStrategyFactory = pricingStrategyFactory;
		this.invoiceRepository = invoiceRepository;
	}


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
		invoice = invoiceRepository.save(invoice);

		return invoice;
	}
}

