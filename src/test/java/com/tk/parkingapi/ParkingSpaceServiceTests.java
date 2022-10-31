package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.exception.GenericConflictException;
import com.tk.parkingapi.exception.GenericNotFoundException;
import com.tk.parkingapi.util.factory.VehicleFactory;
import com.tk.parkingapi.service.model.ParkingSpaceService;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests all the features in the {@link ParkingSpaceService}, such as parking vehicles, finding empty spaces, etc...
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingSpaceServiceTests extends CleanDatabaseContainer {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Autowired
    private VehicleFactory vehicleFactory;

    @Autowired
    private TestUtils testUtils;


    @Test
    public void allStartingSpacesShouldBeEmpty(){
        val parkingSpaces = parkingSpaceService.findAllEmpty();

        parkingSpaces.forEach(space -> Assertions.assertTrue(space.isEmpty()));
    }

    @Test
    public void whenAllSpacesAreFullShouldThrowException(){
        val spaces = parkingSpaceService.findAllEmpty();

        spaces.forEach(space -> {
            val vehicle = vehicleFactory.build();
            parkingSpaceService.parkVehicle(vehicle);
        });

        Assert.assertThrows(GenericNotFoundException.class,
                () -> parkingSpaceService.findOneEmpty());

        Assert.assertThrows(GenericNotFoundException.class,
                () -> parkingSpaceService.findAllEmpty());

        testUtils.unParkAllVehicles();
    }

    @Test
    public void whenUnParkingAnEmptySpaceShouldTrowException(){
        val space = parkingSpaceService.findOneEmpty();
        Assert.assertThrows(GenericNotFoundException.class,
                () -> parkingSpaceService.unParkVehicle(space.getId()));
    }

    @Test
    public void canFindByVehiclePlate(){
        val vehicle = vehicleFactory.build();

        parkingSpaceService.parkVehicle(vehicle);

        val parkedVehiclePlate = parkingSpaceService.findByParkedVehiclePlate(vehicle.getPlate())
                .getVehicle()
                .getPlate();

        Assertions.assertEquals(vehicle.getPlate(), parkedVehiclePlate);

    }

    @Test
    public void cantParkOnOccupiedSpace(){
        val vehicle = vehicleFactory.build();
        val anotherVehicle = vehicleFactory.build();

        val firstEmptySpaceID = parkingSpaceService.findOneEmpty().getId();

        parkingSpaceService.parkVehicleAt(vehicle, firstEmptySpaceID);

        Assert.assertThrows(GenericConflictException.class,
                () -> parkingSpaceService.parkVehicleAt(anotherVehicle, firstEmptySpaceID));
    }

    @Test
    public void whenUpdatingSpacesNoNewOnesShouldBeCreated(){
        val initialSpaceQty = parkingSpaceService.findAll().size();

        val vehicle = vehicleFactory.build();
        parkingSpaceService.parkVehicle(vehicle);

        val endingSpaceQty = parkingSpaceService.findAll().size();

        Assertions.assertEquals(initialSpaceQty, endingSpaceQty);

        testUtils.unParkAllVehicles();
    }

}
