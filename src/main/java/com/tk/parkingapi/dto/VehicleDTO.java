package com.tk.parkingapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class VehicleDTO {
    private String plate;
    private String model;
    private String color;
    private String ownerId;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
}
