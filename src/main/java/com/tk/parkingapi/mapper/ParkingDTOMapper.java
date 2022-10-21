package com.tk.parkingapi.mapper;

import com.tk.parkingapi.dto.ParkingDTO;
import com.tk.parkingapi.entity.ParkingSpace;
import org.springframework.stereotype.Component;

public abstract class ParkingDTOMapper {

    public static ParkingDTO parkingSpaceToDto(ParkingSpace parkingSpace){

        if(parkingSpace.getVehicle() == null) {
            return new ParkingDTO(
                    parkingSpace.getId(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

        return new ParkingDTO(
                parkingSpace.getId(),
                parkingSpace.getVehicle().getPlate(),
                parkingSpace.getVehicle().getModel(),
                parkingSpace.getVehicle().getColor(),
                parkingSpace.getVehicle().getOwnerId(),
                parkingSpace.getVehicle().getEntryDate(),
                parkingSpace.getVehicle().getExitDate(),
                parkingSpace.getVehicle().getBill()
        );
    }
}
