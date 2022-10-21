package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.factory.VehicleFactory;
import com.tk.parkingapi.mapper.ParkingDTOMapper;
import com.tk.parkingapi.service.ParkingSpaceService;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DtoTests extends CleanDatabaseContainer {

    @Autowired
    private VehicleFactory factory;

    @Autowired
    private ParkingSpaceService service;


    @Test
    public void mappingIsCorrect(){
        val vehicle = factory.build();
        val space = service.parkVehicle(vehicle);
        val dto = ParkingDTOMapper.parkingSpaceToDto(space);

        Assert.assertEquals(space.getId(), dto.parkingSpotId());
        Assert.assertEquals(space.getVehicle().getPlate(), dto.vehiclePlate());
        Assert.assertEquals(space.getVehicle().getModel(), dto.vehicleModel());
        Assert.assertEquals(space.getVehicle().getColor(), dto.vehicleColor());
        Assert.assertEquals(space.getVehicle().getOwnerId(), dto.ownerId());
        Assert.assertEquals(space.getVehicle().getEntryDate(), dto.entryDate());
        Assert.assertEquals(space.getVehicle().getExitDate(), dto.exitDate());
        Assert.assertEquals(space.getVehicle().getBill(), dto.bill());
    }

    @Test
    public void canMapEmptyParkingSpace(){
        val space = service.findOneEmpty();
        val dto = ParkingDTOMapper.parkingSpaceToDto(space);

        Assert.assertNull(dto.vehicleModel());
    }
}
