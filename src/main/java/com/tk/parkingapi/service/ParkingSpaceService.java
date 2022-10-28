package com.tk.parkingapi.service;

import com.tk.parkingapi.entity.ParkingSpace;
import com.tk.parkingapi.entity.Vehicle;
import com.tk.parkingapi.exception.*;
import com.tk.parkingapi.repository.ParkingSpaceRepository;
import com.tk.parkingapi.service.billing.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpaceService {

    private final ParkingSpaceRepository repository;
    private final BillingService billing;

    @Transactional
    public ParkingSpace find(int id){
        return repository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("No space exists with id " + id));

    }

    @Transactional
    public void save(ParkingSpace space) {
        repository.save(space);
    }
    @Transactional
    public List<ParkingSpace> findAll(){
        return repository.findAll();
    }

    @Transactional
    public List<ParkingSpace> findAllEmpty(){
        val list = repository.findByVehicleIsNull();

        if(list.isEmpty()) throw new GenericNotFoundException("There are no empty parking spaces");

        return list;

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

    // TODO: Save log
    @Transactional
    public Vehicle unParkVehicle(int parkingSpaceID){
        val space = find(parkingSpaceID);
        return  unParkVehicle(space);
    }
    @Transactional
    public Vehicle unParkVehicle(String vehiclePlate) {
        val space = findByParkedVehiclePlate(vehiclePlate);
        return unParkVehicle(space);
    }
    @Transactional
    private Vehicle unParkVehicle(ParkingSpace space){
        val vehicle = space.getVehicle();
        if(vehicle == null) throw new GenericNotFoundException("The space " + space.getId() + " is empty");

        space.setVehicle(null);
        repository.save(space);

        vehicle.setExitDate(LocalDateTime.now());
        val bill = billing.calculateBill(vehicle.getEntryDate(), vehicle.getExitDate());
        vehicle.setBill(bill);

        return vehicle;
    }


    @Transactional
    public ParkingSpace findByParkedVehiclePlate(String vehiclePlate){
        return repository.findByVehiclePlate(vehiclePlate)
                .orElseThrow(() -> new GenericNotFoundException("There is no parked vehicle by plate " + vehiclePlate));

    }

    @Transactional
    public ParkingSpace parkVehicleAt(Vehicle vehicle, int parkingSpaceId){
        val space = this.find(parkingSpaceId);

        if(!space.isEmpty()) throw new GenericConflictException("The parking space " + space.getId() + " is already occupied by the vehicle of plate " + space.getVehicle().getPlate());

        space.setVehicle(vehicle);

        repository.save(space);

        return space;
    }

    @Transactional
    public ParkingSpace parkVehicleAt(Vehicle vehicle, ParkingSpace space) {
        if(space.getVehicle() != null) throw new GenericConflictException(
                String.format("The space %s is already occupied by the car of plate %s", space.getId(), space.getVehicle().getPlate())
        );

        space.setVehicle(vehicle);
        repository.save(space);

        return space;
    }
}
