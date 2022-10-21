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

        System.out.println(dto);

        Assert.assertEquals(space.getId(), dto.getId());
        Assert.assertEquals(space.getVehicle().getPlate(), dto.getVehiclePlate());
        Assert.assertEquals(space.getVehicle().getModel(), dto.getVehicleModel());
        Assert.assertEquals(space.getVehicle().getColor(), dto.getVehicleColor());
        Assert.assertEquals(space.getVehicle().getOwnerId(), dto.getVehicleOwnerId());
        Assert.assertEquals(space.getVehicle().getEntryDate(), dto.getVehicleEntryDate());
        Assert.assertEquals(space.getVehicle().getExitDate(), dto.getVehicleExitDate());
        Assert.assertEquals(space.getVehicle().getBill(), dto.getVehicleBill());
    }

    @Test
    public void canMapEmptyParkingSpace(){
        val space = service.findOneEmpty();
        val dto = ParkingDTOMapper.parkingSpaceToDto(space);

        Assert.assertNull(dto.getVehicleModel());
    }
}
