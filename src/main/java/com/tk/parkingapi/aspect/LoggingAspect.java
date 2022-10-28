package com.tk.parkingapi.aspect;

import com.tk.parkingapi.entity.ParkingHistory;
import com.tk.parkingapi.entity.Vehicle;
import com.tk.parkingapi.repository.ParkingHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final ParkingHistoryRepository repository;

    @Pointcut("execution(* com.tk.parkingapi.service.ParkingSpaceService.unParkVehicle(*))")
    private void afterUnParkingVehiclePointcut(){}

    @AfterReturning(pointcut = "afterUnParkingVehiclePointcut()", returning = "vehicle")
    public void afterUnParkingVehicleAdvice(JoinPoint joinPoint, Vehicle vehicle){
        val parkingHistory = new ParkingHistory(
                vehicle.getPlate(),
                vehicle.getEntryDate(),
                vehicle.getExitDate(),
                vehicle.getBill()
        );

        repository.save(parkingHistory);
    }
}
