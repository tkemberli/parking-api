package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.repository.ParkingHistoryRepository;
import com.tk.parkingapi.repository.ParkingSpaceRepository;
import com.tk.parkingapi.repository.VehicleRepository;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseSetupTests extends CleanDatabaseContainer{


    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;
    @Autowired
    private ParkingHistoryRepository parkingHistoryRepository;
    @Autowired
    private VehicleRepository vehicleRepository;


    @Test
    void shouldHaveXparkingSpaces(){

        int parkingSpaceQty = 10;
        val parkingSpaceList = parkingSpaceRepository.findAll();

        Assertions.assertEquals(parkingSpaceQty, parkingSpaceList.size());
    }

    @Test
    void parkingHistoryShouldBeEmpty(){
        val parkingHistory = parkingHistoryRepository.findAll();

        Assertions.assertEquals(0, parkingHistory.size());
    }

    @Test
    void shouldHaveNoParkingVehicles(){
        val vehicles = vehicleRepository.findAll();

        Assertions.assertEquals(0, vehicles.size());

    }
}
