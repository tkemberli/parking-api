package com.tk.parkingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @Column(name = "plate")
    private String plate;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "entry_date")
    private LocalDateTime entryDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "bill")
    private Double bill;
}
