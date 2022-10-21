package com.tk.parkingapi.controller;

import com.tk.parkingapi.dto.ParkingDTO;
import com.tk.parkingapi.mapper.ParkingDTOMapper;
import com.tk.parkingapi.service.ParkingSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
