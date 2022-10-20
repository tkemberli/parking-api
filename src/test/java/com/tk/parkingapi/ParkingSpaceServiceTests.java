package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.exception.NoEmptyParkingSpaceFoundException;
import com.tk.parkingapi.exception.ParkingSpaceEmptyException;
import com.tk.parkingapi.exception.ParkingSpaceIsNotEmptyException;
import com.tk.parkingapi.factory.VehicleFactory;
import com.tk.parkingapi.service.ParkingSpaceService;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingSpaceServiceTests extends CleanDatabaseContainer {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Autowired
    private VehicleFactory vehicleFactory;

    // Not putting this on a @AfterAll annotation, since that would make the method need to be static
    // and a static method can't use the parkingSpaceService declared above
    public void unParkAllVehicles(){
        val spaces = parkingSpaceService.findAll();
        spaces.forEach(space -> {
            try{
                parkingSpaceService.unParkVehicle(space.getId());
            } catch (ParkingSpaceEmptyException exception){}
        });
    }

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

        Assert.assertThrows(NoEmptyParkingSpaceFoundException.class,
                () -> parkingSpaceService.findOneEmpty());

        Assert.assertThrows(NoEmptyParkingSpaceFoundException.class,
                () -> parkingSpaceService.findAllEmpty());

        unParkAllVehicles();
    }

    @Test
    public void whenUnParkingAnEmptySpaceShouldTrowException(){
        val space = parkingSpaceService.findOneEmpty();
        Assert.assertThrows(ParkingSpaceEmptyException.class,
                () -> parkingSpaceService.unParkVehicle(space.getId()));
    }

    @Test
    public void canFindByVehiclePlate(){
        val vehicle = vehicleFactory.build();

        parkingSpaceService.parkVehicle(vehicle);

        val parkedVehiclePlate = parkingSpaceService.findByParkedVehiclePlate(vehicle.getPlate())
                .getVehicle()
                .getPlate();

        Assert.assertEquals(vehicle.getPlate(), parkedVehiclePlate);

    }

    @Test
    public void cantParkOnOccupiedSpace(){
        val vehicle = vehicleFactory.build();
        val anotherVehicle = vehicleFactory.build();

        val firstEmptySpaceID = parkingSpaceService.findOneEmpty().getId();

        parkingSpaceService.parkVehicleAt(vehicle, firstEmptySpaceID);

        Assert.assertThrows(ParkingSpaceIsNotEmptyException.class,
                () -> parkingSpaceService.parkVehicleAt(anotherVehicle, firstEmptySpaceID));
    }

    @Test
    public void whenUpdatingSpacesNoNewOnesShouldBeCreated(){
        val initialSpaceQty = parkingSpaceService.findAll().size();

        val vehicle = vehicleFactory.build();
        parkingSpaceService.parkVehicle(vehicle);

        val endingSpaceQty = parkingSpaceService.findAll().size();

        Assert.assertEquals(initialSpaceQty, endingSpaceQty);

        unParkAllVehicles();
    }

}
