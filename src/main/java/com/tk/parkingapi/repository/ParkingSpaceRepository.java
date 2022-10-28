package com.tk.parkingapi.repository;

import com.tk.parkingapi.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {
    List<ParkingSpace> findByVehicleIsNull();

    Optional<ParkingSpace> findByVehiclePlate(String vehiclePlate);
}
