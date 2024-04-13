package com.example.strategies.pricing;

import java.util.*;


public class PricingStrategyFactory {

	public PricingStrategy getPricingStrategy(Date exitTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(exitTime);

		// If the exit time of the vehicle is on weekend then weekend pricing strategy is applicable for billing
		if(cal.get(Calendar.DAY_OF_WEEK)==1 || cal.get(Calendar.DAY_OF_WEEK)==7) {
			return new WeekendPricingStrategy();
		}
		// If the exit time of the vehicle is on weekday then weekday pricing strategy is applicable for billing
		else {
			return new WeekdayPricingStrategy();
		}
	}

}
