package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.*;

import java.util.Optional;


public interface TicketRepository {

	public Ticket save(Ticket ticket);

	public Optional<Ticket> getTicketById(long ticketId);

}
