package com.example.respositories;

import java.util.*;
import com.example.models.Ticket;
import org.springframework.stereotype.Repository;


@Repository
public class TicketRepository {

	private Map<Long, Ticket> map;

	public TicketRepository() {
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
