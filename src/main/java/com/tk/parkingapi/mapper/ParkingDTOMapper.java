package com.tk.parkingapi.mapper;

import com.tk.parkingapi.dto.ParkingDTO;
import com.tk.parkingapi.entity.ParkingSpace;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public abstract class ParkingDTOMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static ParkingDTO parkingSpaceToDto(ParkingSpace parkingSpace){
        return MODEL_MAPPER.map(parkingSpace, ParkingDTO.class);
    }
}
