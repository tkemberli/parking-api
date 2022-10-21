package com.tk.parkingapi.dto;


import java.time.LocalDateTime;

public record ParkingDTO(
        int parkingSpotId,
        String vehiclePlate,
        String vehicleModel,
        String vehicleColor,
        String ownerId,
        LocalDateTime entryDate,
        LocalDateTime exitDate,
        Double bill
){}
