package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.Invoice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateInvoiceResponseDto {
	
    private ResponseStatus status;

    private Invoice invoice;

}
