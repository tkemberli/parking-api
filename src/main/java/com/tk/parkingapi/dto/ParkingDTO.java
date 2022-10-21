package com.tk.parkingapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ParkingDTO {
    int id;
    String vehiclePlate;
    String vehicleModel;
    String vehicleColor;
    String vehicleOwnerId;
    LocalDateTime vehicleEntryDate;
    LocalDateTime vehicleExitDate;
    Double vehicleBill;
}
