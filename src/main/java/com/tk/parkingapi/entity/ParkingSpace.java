package com.tk.parkingapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class ParkingSpace {
    @Id
    private int id;
    private Car car;

    public boolean isEmpty(){
        return this.car == null;
    }
}
