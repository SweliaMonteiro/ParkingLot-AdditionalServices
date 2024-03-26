package com.scaler.parking_lot.controllers;

import com.scaler.parking_lot.dtos.GenerateInvoiceRequestDto;
import com.scaler.parking_lot.dtos.GenerateInvoiceResponseDto;
import com.scaler.parking_lot.dtos.ResponseStatus;
import com.scaler.parking_lot.services.InvoiceService;


public class InvoiceController {

	private InvoiceService invoiceService;

	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	public GenerateInvoiceResponseDto createInvoice(GenerateInvoiceRequestDto requestDto){
		
		GenerateInvoiceResponseDto response = new GenerateInvoiceResponseDto();

		try {
			response.setInvoice(invoiceService.createInvoice(requestDto.getTicketId(), requestDto.getGateId()));
			response.setStatus(ResponseStatus.SUCCESS);
		}
		catch(Exception e) {
			response.setStatus(ResponseStatus.FAILURE);
		}

		return response;
	}

}
