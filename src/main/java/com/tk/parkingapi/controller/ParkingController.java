package com.tk.parkingapi.controller;

import com.tk.parkingapi.dto.ParkingDTO;
import com.tk.parkingapi.dto.VehicleDTO;
import com.tk.parkingapi.entity.ParkingHistory;
import com.tk.parkingapi.entity.ParkingSpace;
import com.tk.parkingapi.exception.GenericConflictException;
import com.tk.parkingapi.exception.GenericNotFoundException;
import com.tk.parkingapi.mapper.ParkingDTOMapper;
import com.tk.parkingapi.service.ParkingHistoryService;
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

    private final ParkingSpaceService parkingService;
    private final ParkingHistoryService historyService;


    // TODO: Return a DTO instead?
    @ApiOperation("Shows the parking history")
    @GetMapping("/")
    public ResponseEntity<List<ParkingHistory>> findAllHistory(){
        val list = historyService.findAll();

        return ResponseEntity.ok(list);
    }

    @ApiOperation("Get all parking spaces")
    @GetMapping("/space")
    public ResponseEntity<List<ParkingDTO>> findAll(){
        val list = parkingService.findAll();
        val dtoList = ParkingDTOMapper.parkingSpaceToDto(list);

        return ResponseEntity.ok(dtoList);
    }

    @ApiOperation("Get all empty parking spaces")
    @GetMapping("/space/empty")
    public ResponseEntity<List<ParkingDTO>> findAllEmpty(){
        val list = parkingService.findAllEmpty();
        val dtoList = ParkingDTOMapper.parkingSpaceToDto(list);

        return ResponseEntity.ok(dtoList);
    }

    @ApiOperation("Get a specific parking space by space ID")
    @GetMapping("/space/{spaceId}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable int spaceId){
        val space = parkingService.find(spaceId);
        val spaceDTO = ParkingDTOMapper.parkingSpaceToDto(space);

        return ResponseEntity.ok(spaceDTO);
    }

    @ApiOperation("Get a specific parking space by car plate")
    @GetMapping("/vehicle/{plate}")
    public ResponseEntity<ParkingDTO> findByVehiclePlate(@PathVariable String plate){
        val space = parkingService.findByParkedVehiclePlate(plate);
        val spaceDTO = ParkingDTOMapper.parkingSpaceToDto(space);

        return ResponseEntity.ok(spaceDTO);
    }

    @ApiOperation("Park a vehicle in any empty space")
    @PutMapping("/")
    public ResponseEntity<ParkingDTO> parkVehicle(@RequestBody VehicleDTO vehicleDTO) {
        val vehicle = ParkingDTOMapper.dtoToVehicle(vehicleDTO);

        val space = parkingService.parkVehicle(vehicle);
        val spaceDTO = ParkingDTOMapper.parkingSpaceToDto(space);

        return ResponseEntity.ok(spaceDTO);
    }

    @ApiOperation("Park a vehicle in a specific space")
    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> parkVehicle(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) {
        val vehicle = ParkingDTOMapper.dtoToVehicle(vehicleDTO);
        val space = parkingService.parkVehicleAt(vehicle, id);
        val spaceDTO = ParkingDTOMapper.parkingSpaceToDto(space);

        return ResponseEntity.ok(spaceDTO);
    }

    @ApiOperation("Unpark a vehicle from a space, by space ID")
    @DeleteMapping("/{spaceId}")
    public ResponseEntity<VehicleDTO> unparkVehicle(@PathVariable int spaceId) {
        val space = parkingService.find(spaceId);

        // TODO: Return an vehicle optional?
        val vehicle = space.getVehicle();

        if(vehicle == null) throw new GenericNotFoundException("There is no vehicle parked in the spot " + spaceId);

        space.setVehicle(null);

        parkingService.save(space);

        val vehicleDTO = ParkingDTOMapper.vehicleToDTO(vehicle);

        return ResponseEntity.status(HttpStatus.OK).body(vehicleDTO);
    }

    @ApiOperation("Unpark a vehicle from a space, by vehicle plate")
    @DeleteMapping("/vehicle/{vehiclePlate}")
    public ResponseEntity<VehicleDTO> unParkVehicle(@PathVariable String vehiclePlate) {
        val vehicle = parkingService.unParkVehicle(vehiclePlate);

        val vehicleDTO = ParkingDTOMapper.vehicleToDTO(vehicle);

        return ResponseEntity.status(HttpStatus.OK).body(vehicleDTO);
    }
}
