package com.tk.parkingapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "parking_space")
@Data
@NoArgsConstructor
public class ParkingSpace {
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle car;

    public boolean isEmpty(){
        return this.car == null;
    }
}
