package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.ResponseStatus;
import com.example.models.Ticket;
import com.example.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class TicketController {

	@Autowired
	private TicketService ticketService;


	public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto) {
		GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
		try{
			Ticket ticket = ticketService.generateTicket(requestDto.getGateId(), requestDto.getRegistrationNumber(), requestDto.getVehicleType(), requestDto.getAdditionalServices());
			responseDto.setResponseStatus(ResponseStatus.SUCCESS);
			responseDto.setTicket(ticket);
		} 
		catch (Exception e){
			responseDto.setResponseStatus(ResponseStatus.FAILURE);
		}
		return responseDto;
	}
}
