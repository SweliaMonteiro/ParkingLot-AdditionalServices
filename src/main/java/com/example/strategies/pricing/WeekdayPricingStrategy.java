package com.example.strategies.pricing;

import com.example.enums.VehicleType;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


public class WeekdayPricingStrategy implements PricingStrategy{

	@Override
	public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
		double amount = 0;

		// Inorder to find the difference between the entryTime and exitTime, convert gives dates to LocalTime 
		Calendar calEntryTime = Calendar.getInstance();
		calEntryTime.setTime(entryTime);
		LocalTime localTimeEntryTime = LocalTime.of(calEntryTime.get(Calendar.HOUR_OF_DAY), calEntryTime.get(Calendar.MINUTE), calEntryTime.get(Calendar.SECOND)); 

		Calendar calExitTime = Calendar.getInstance();
		calExitTime.setTime(exitTime);
		LocalTime localTimeExitTime = LocalTime.of(calExitTime.get(Calendar.HOUR_OF_DAY), calExitTime.get(Calendar.MINUTE), calExitTime.get(Calendar.SECOND)); 

		// Find what is the difference in hours between entryTime and exitTime
		double hoursDiff = ChronoUnit.HOURS.between(localTimeEntryTime, localTimeExitTime); 

		// Find what is the difference in minutes between entryTime and exitTime, divide by 100 as we want minutes to come after decimal point
		// ex. 1 hour 30 minutes we want it as 1.30 therefore get the 30 minutes and divide by 100 to get 0.30 
		double minutesDiff  = (ChronoUnit.MINUTES.between(localTimeEntryTime, localTimeExitTime) % 60.0) / 100.0; 

		// Find the upper limit of addition of hoursDiff and minutesDiff, multiply by 10 as per hour the rate is 10
		amount = (int)Math.ceil(hoursDiff+minutesDiff) * 10; 

		return amount;
	}

}
