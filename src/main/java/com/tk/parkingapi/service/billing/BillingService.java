package com.tk.parkingapi.service.billing;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;

/**
 * Service for calculating the bill value when unparking a vehicle
 */

@Component
public class BillingService {
    private final Map<Integer, Double> hourlyRates;

    /**
     * The constructor takes the prices from the configuration file
     * @param hourlySteps describes in how many hours should the rate change
     * @param hourlyValues describes hourly values per steps
     */
    public BillingService(
            @Value("${billing.hourlySteps}") String hourlySteps,
            @Value("${billing.hourlyValues}") String hourlyValues) {

        val steps = hourlySteps.split(",");
        val values = hourlyValues.split(",");

        hourlyRates = new TreeMap<>();

        for(int i = 0; i < steps.length; i++){
            hourlyRates.put(Integer.valueOf(steps[i]), Double.valueOf(values[i]));
        }
    }

    public double calculateBill(LocalDateTime entryDate, LocalDateTime exitDate){
        val timeDifference = ChronoUnit.HOURS.between(entryDate, exitDate);

        for(int step : hourlyRates.keySet()) {
            if(timeDifference <= step) return timeDifference * hourlyRates.get(step);
        }

        return timeDifference * hourlyRates.get(24);
    }
}

