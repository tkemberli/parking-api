package com.tk.parkingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingHistory {

    @Column(name = "parking_space_id")
    private int parkingSpaceId;

    @Column(name = "vehicle_plate")
    private String vehiclePlate;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "bill")
    private double bill;
}
