package com.tk.parkingapi.controller;

import com.tk.parkingapi.dto.ParkingDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
//
//    @ApiOperation("Get all parking spaces")
//    @GetMapping
//    public ResponseEntity<List<ParkingDTO>> findAll(){
//
//    }
}
