package com.tk.parkingapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ParkingSpace {
    @Id
    @Column(name = "id")
    private int id;
    private Vehicle car;

    public boolean isEmpty(){
        return this.car == null;
    }
}
