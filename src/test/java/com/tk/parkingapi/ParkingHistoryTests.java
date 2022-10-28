package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.util.factory.VehicleFactory;
import com.tk.parkingapi.repository.ParkingHistoryRepository;
import com.tk.parkingapi.service.model.ParkingSpaceService;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingHistoryTests extends CleanDatabaseContainer {

    @Autowired
    private ParkingHistoryRepository repository;

    @Autowired
    private ParkingSpaceService service;

    @Autowired
    private VehicleFactory factory;

    @Test
    @Order(1)
    public void onExceptionNoLoggingShouldBeDone(){

        try{
            service.unParkVehicle(1);
        }catch (Exception e) {}
        finally {
            Assertions.assertTrue(repository.findAll().isEmpty());
        }
    }

    @Test
    public void loggingAspectIsCorrectlyConfigured(){
        val vehicle = factory.build();
        service.parkVehicle(vehicle);
        service.unParkVehicle(vehicle.getPlate());

        Assertions.assertFalse(repository.findAll().isEmpty());
    }

}
