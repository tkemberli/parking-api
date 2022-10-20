package com.tk.parkingapi.repository;

import com.tk.parkingapi.entity.ParkingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingHistoryRepository extends JpaRepository<ParkingHistory, Integer> {
}
