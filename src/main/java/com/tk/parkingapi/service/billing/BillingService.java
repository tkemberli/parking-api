package com.tk.parkingapi.service.billing;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class BillingService {
    private final Map<Integer, Double> hourlyRates;

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

        return 0.0d;
    }
}

