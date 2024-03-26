package com.scaler.parking_lot.strategies.pricing;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.scaler.parking_lot.models.Slab;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.SlabRepository;


public class WeekendPricingStrategy implements PricingStrategy{

	private SlabRepository slabRepository;

	public WeekendPricingStrategy(SlabRepository slabRepository) {
		this.slabRepository = slabRepository;
	}


	@Override
	public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
		
		// Get all the slab based pricing from DB as given in the question sorted by vehicle type
		List<Slab> slabs = slabRepository.getSortedSlabsByVehicleType(vehicleType);
		
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
		int hoursSpent = (int)Math.ceil(hoursDiff+minutesDiff);

		double amount = 0;
		
		// Loop through all the slabs available from the DB
		for(Slab slab:slabs) {
			// If the hours spent are greater than the end hour from the slab and slab end hour is not equal to -1, 
			// then get the difference between the hours (start and end hour of slab) and multiply by the slab price as the price mentioned in slab is for per hour and add it to total amount
			if(hoursSpent>slab.getEndHour() && slab.getEndHour()!=-1) { 
				amount += (slab.getEndHour() - slab.getStartHour()) * slab.getPrice();
			}
			// If no upper limit for the slab(i.e. -1) or if the hours spent are less than end hour of the slab,
			// then get the difference between the hours (start hour of slab and calculated end hour) and multiply by the slab price as the price mentioned in slab is for per hour and add it to total amount
			else if(slab.getEndHour()==-1 || hoursSpent<=slab.getEndHour()){
				amount += (hoursSpent - slab.getStartHour()) * slab.getPrice();
				break;
			}
		}
		
		return amount;
	}
}
