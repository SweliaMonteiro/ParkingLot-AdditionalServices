package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Ticket;


public class TicketRepositoryImpl implements TicketRepository {

	private Map<Long, Ticket> map;

	public TicketRepositoryImpl() {
		map = new HashMap<Long, Ticket>();
	}

	public Ticket save(Ticket ticket) {
		if(ticket.getId()==0) {
			ticket.setId(map.size()+1);
		}
		map.put(ticket.getId(), ticket);
		return ticket;
	}

	public Optional<Ticket> getTicketById(long ticketId) {
		return Optional.ofNullable(map.get(ticketId));
	}
}