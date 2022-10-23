package com.tk.parkingapi.controller;

import com.tk.parkingapi.dto.ParkingDTO;
import com.tk.parkingapi.dto.VehicleDTO;
import com.tk.parkingapi.entity.ParkingSpace;
import com.tk.parkingapi.exception.GenericConflictException;
import com.tk.parkingapi.mapper.ParkingDTOMapper;
import com.tk.parkingapi.service.ParkingSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Api("Parking API")
public class ParkingController {

    private final ParkingSpaceService service;

    @ApiOperation("Get all parking spaces")
    @GetMapping
    public ResponseEntity<List<ParkingDTO>> findAll(){
        val list = service.findAll();
        val dtoList = ParkingDTOMapper.parkingSpaceToDto(list);

        return ResponseEntity.ok(dtoList);
    }

    @ApiOperation("Get all empty parking spaces")
    @GetMapping("/empty")
    public ResponseEntity<List<ParkingDTO>> findAllEmpty(){
        val list = service.findAllEmpty();
        val dtoList = ParkingDTOMapper.parkingSpaceToDto(list);

        return ResponseEntity.ok(dtoList);
    }

    @ApiOperation("Park a vehicle in any empty space")
    @PutMapping("/park")
    public ResponseEntity<ParkingDTO> parkVehicle(@RequestBody VehicleDTO vehicleDTO) {
        val space = service.findOneEmpty();

        val spaceDTO = parkVehicleAt(vehicleDTO, space);

        return ResponseEntity.status(HttpStatus.OK).body(spaceDTO);

    }

    @ApiOperation("Park a vehicle in a specific space")
    @PutMapping("/park/{id}")
    public ResponseEntity<ParkingDTO> parkVehicle(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) {
        val space = service.find(id);

        val spaceDTO = parkVehicleAt(vehicleDTO, space);

        return ResponseEntity.status(HttpStatus.OK).body(spaceDTO);
    }

    private ParkingDTO parkVehicleAt(VehicleDTO vehicleDTO, ParkingSpace space) {
        val vehicle = ParkingDTOMapper.dtoToVehicle(vehicleDTO);

        if(space.getVehicle() != null) throw new GenericConflictException(
                String.format("The space %s is already occupied by the car of plate %s", space.getId(), space.getVehicle().getPlate())
        );

        space.setVehicle(vehicle);
        service.save(space);

        val spaceDTO = ParkingDTOMapper.parkingSpaceToDto(space);

        return spaceDTO;
    }
}
