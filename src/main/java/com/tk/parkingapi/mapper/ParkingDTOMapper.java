package com.tk.parkingapi.mapper;

import com.tk.parkingapi.dto.ParkingDTO;
import com.tk.parkingapi.dto.VehicleDTO;
import com.tk.parkingapi.entity.ParkingSpace;
import com.tk.parkingapi.entity.Vehicle;
import org.modelmapper.ModelMapper;

import java.util.List;

public abstract class ParkingDTOMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public static ParkingDTO parkingSpaceToDto(ParkingSpace parkingSpace){
        return MODEL_MAPPER.map(parkingSpace, ParkingDTO.class);
    }

    public static List<ParkingDTO> parkingSpaceToDto(List<ParkingSpace> parkingSpaces){
        return parkingSpaces.stream().map(ParkingDTOMapper::parkingSpaceToDto).toList();
    }

    public static Vehicle dtoToVehicle(VehicleDTO dto) {
        return MODEL_MAPPER.map(dto, Vehicle.class);
    }

    public static VehicleDTO vehicleToDTO(Vehicle vehicle) {
        return MODEL_MAPPER.map(vehicle, VehicleDTO.class);
    }
}
