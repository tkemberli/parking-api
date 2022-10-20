package com.tk.parkingapi.repository;

import com.tk.parkingapi.entity.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {
}
