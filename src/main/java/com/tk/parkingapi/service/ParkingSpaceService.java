package com.tk.parkingapi.service;

import com.tk.parkingapi.entity.ParkingSpace;
import com.tk.parkingapi.entity.Vehicle;
import com.tk.parkingapi.exception.*;
import com.tk.parkingapi.repository.ParkingSpaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpaceService {

    private final ParkingSpaceRepository repository;

    @Transactional
    public ParkingSpace find(int id){
        val space = repository.findById(id);
        if(space.isEmpty()) throw new ParkingSpaceNotFoundException(id);

        return space.get();
    }
    @Transactional
    public List<ParkingSpace> findAll(){
        return repository.findAll();
    }

    @Transactional
    public List<ParkingSpace> findAllEmpty(){
        val parkingSpaces = repository.findByVehicleIsNull();

        if(parkingSpaces.isEmpty()) throw new NoEmptyParkingSpaceFoundException();

        return parkingSpaces;
    }

    @Transactional
    public ParkingSpace findOneEmpty(){
        return findAllEmpty().get(0);
    }

    @Transactional
    public ParkingSpace parkVehicle(Vehicle vehicle){
        val parkingSpace = findOneEmpty();
        parkingSpace.setVehicle(vehicle);
        repository.save(parkingSpace);

        return parkingSpace;
    }

    // TODO: Calculate the bill
    @Transactional
    public Vehicle unParkVehicle(int parkingSpaceID){
        val space = find(parkingSpaceID);
        val vehicle = space.getVehicle();

        if(vehicle == null) throw new ParkingSpaceEmptyException(parkingSpaceID);

        space.setVehicle(null);
        return vehicle;

    }

    @Transactional
    public ParkingSpace findByParkedVehiclePlate(String vehiclePlate){
        val space = repository.findByVehiclePlate(vehiclePlate);

        if(space.isEmpty()) throw new VehicleNotFoundException(vehiclePlate);

        return space.get();
    }

    @Transactional
    public ParkingSpace parkVehicleAt(Vehicle vehicle, int parkingSpaceId){
        val space = this.find(parkingSpaceId);

        if(!space.isEmpty()) throw new ParkingSpaceIsNotEmptyException(space);

        space.setVehicle(vehicle);

        repository.save(space);

        return space;
    }
}
