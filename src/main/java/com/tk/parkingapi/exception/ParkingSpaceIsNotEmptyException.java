package com.tk.parkingapi.exception;

import com.tk.parkingapi.entity.ParkingSpace;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ParkingSpaceIsNotEmptyException extends RuntimeException{
    public ParkingSpaceIsNotEmptyException(ParkingSpace space) {

        super("The parking space of id " + space.getId() +
                " is already occupied by the vehicle of plate " + space.getVehicle().getPlate());
    }
}
