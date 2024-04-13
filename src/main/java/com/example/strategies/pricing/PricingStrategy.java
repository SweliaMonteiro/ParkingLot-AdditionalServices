package com.example.strategies.pricing;

import com.example.enums.VehicleType;
import java.util.Date;

public interface PricingStrategy {

	double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType);

}
