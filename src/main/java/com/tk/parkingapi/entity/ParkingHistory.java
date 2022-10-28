package com.tk.parkingapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_history")
@Data
@NoArgsConstructor
public class ParkingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vehicle_plate")
    private String vehiclePlate;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "bill")
    private double bill;

    public ParkingHistory(String vehiclePlate, LocalDateTime entryDate, LocalDateTime exitDate, double bill) {
        this.vehiclePlate = vehiclePlate;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.bill = bill;
    }
}
