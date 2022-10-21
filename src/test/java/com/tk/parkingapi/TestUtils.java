package com.tk.parkingapi;

import com.tk.parkingapi.factory.VehicleFactory;
import com.tk.parkingapi.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestUtils {

    private final ParkingSpaceService service;
    private final VehicleFactory factory;

    public void unParkAllVehicles(){
        val spaces = service.findAll();
        spaces.forEach(space -> {
            try{
                service.unParkVehicle(space.getId());
            } catch (Exception exception){}
        });
    }

    public void parkOnAllSpaces(){
        val spaces = service.findAll();
        spaces.forEach(space -> {
            try{
                val vehicle = factory.build();
                service.parkVehicleAt(vehicle, space.getId());

            } catch (Exception exception){}
        });

    }
}
