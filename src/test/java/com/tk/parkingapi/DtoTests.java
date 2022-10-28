package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.util.factory.VehicleFactory;
import com.tk.parkingapi.util.mapper.ParkingDTOMapper;
import com.tk.parkingapi.service.model.ParkingSpaceService;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
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

        Assertions.assertEquals(space.getId(), dto.getId());
        Assertions.assertEquals(space.getVehicle().getPlate(), dto.getVehiclePlate());
        Assertions.assertEquals(space.getVehicle().getModel(), dto.getVehicleModel());
        Assertions.assertEquals(space.getVehicle().getColor(), dto.getVehicleColor());
        Assertions.assertEquals(space.getVehicle().getOwnerId(), dto.getVehicleOwnerId());
        Assertions.assertEquals(space.getVehicle().getEntryDate(), dto.getVehicleEntryDate());
        Assertions.assertEquals(space.getVehicle().getExitDate(), dto.getVehicleExitDate());
        Assertions.assertEquals(space.getVehicle().getBill(), dto.getVehicleBill());
    }

    @Test
    public void canMapEmptyParkingSpace(){
        val space = service.findOneEmpty();
        val dto = ParkingDTOMapper.parkingSpaceToDto(space);

        Assertions.assertNull(dto.getVehicleModel());
    }
}
