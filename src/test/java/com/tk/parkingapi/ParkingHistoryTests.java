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

/**
 * Tests if after taking a vehicle out of a spot, the parking history is saved to the database
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingHistoryTests extends CleanDatabaseContainer {

    @Autowired
    private ParkingHistoryRepository repository;

    @Autowired
    private ParkingSpaceService service;

    @Autowired
    private VehicleFactory factory;

    @Autowired
    private TestUtils testUtils;

    /**
     * Since the database container is reused between tests, we have to clean the history table here, in order to
     * test if no history is saved when an exception is thrown
     */
    @Test
    @Order(1)
    public void onExceptionNoLoggingShouldBeDone(){

        try{
            testUtils.unParkAllVehicles();
            testUtils.clearParkingHistory();
            service.unParkVehicle(1);
        }catch (Exception e) {}
        finally {
            Assertions.assertTrue(repository.findAll().isEmpty());
        }
    }

    @Test
    @Order(2)
    public void loggingAspectIsCorrectlyConfigured(){
        val vehicle = factory.build();
        service.parkVehicle(vehicle);
        service.unParkVehicle(vehicle.getPlate());

        Assertions.assertFalse(repository.findAll().isEmpty());
    }
}