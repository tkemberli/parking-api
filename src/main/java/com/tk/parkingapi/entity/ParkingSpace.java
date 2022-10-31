package com.tk.parkingapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Represents spaces where vehicles can be parkeds
 */

@Entity
@Table(name = "parking_space")
@Data
@NoArgsConstructor
public class ParkingSpace {
    @Id
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_plate", referencedColumnName = "plate")
    private Vehicle vehicle;

    public boolean isEmpty(){
        return this.vehicle == null;
    }
}
