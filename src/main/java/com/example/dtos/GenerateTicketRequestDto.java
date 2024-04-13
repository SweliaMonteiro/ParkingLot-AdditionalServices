package com.example.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class GenerateTicketRequestDto {
	
    private int gateId;

    private String registrationNumber;

    private String vehicleType;

    private List<String> additionalServices;

}
