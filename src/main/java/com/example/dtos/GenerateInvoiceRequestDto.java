package com.example.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateInvoiceRequestDto {
    
    private long ticketId;

    private long gateId;

}
