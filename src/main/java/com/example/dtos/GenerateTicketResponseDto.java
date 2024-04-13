package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateTicketResponseDto {

    private Ticket ticket;

    private ResponseStatus responseStatus;

}
