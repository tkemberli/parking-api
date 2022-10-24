package com.tk.parkingapi.service;

import com.tk.parkingapi.entity.ParkingHistory;
import com.tk.parkingapi.repository.ParkingHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingHistoryService {
    private final ParkingHistoryRepository repository;

    public List<ParkingHistory> findAll(){
        return  repository.findAll(Sort.by("exitDate"));
    }
}
