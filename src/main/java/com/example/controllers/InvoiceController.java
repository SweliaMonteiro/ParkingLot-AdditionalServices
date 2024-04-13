package com.example.controllers;

import com.example.dtos.*;
import com.example.enums.ResponseStatus;
import com.example.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;


	public GenerateInvoiceResponseDto createInvoice(GenerateInvoiceRequestDto requestDto) {
		GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();
		try {
			responseDto.setInvoice(invoiceService.createInvoice(requestDto.getTicketId(), requestDto.getGateId()));
			responseDto.setStatus(ResponseStatus.SUCCESS);
		}
		catch(Exception e) {
			responseDto.setStatus(ResponseStatus.FAILURE);
		}
		return responseDto;
	}
}
