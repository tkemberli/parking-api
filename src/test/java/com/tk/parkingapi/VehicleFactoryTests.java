package com.tk.parkingapi;

import com.tk.parkingapi.util.factory.VehicleFactory;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests if the {@link VehicleFactory} is correctly building vehicles
 */

public class VehicleFactoryTests {

    private VehicleFactory factory;

    @BeforeEach
    public void initialize(){
        factory = new VehicleFactory();
    }

    @Test
    public void shouldBuildWithAllProperties(){
        val car = factory.build();
        Assertions.assertNotNull(car.getPlate());
        Assertions.assertNotNull(car.getModel());
        Assertions.assertNotNull(car.getColor());
        Assertions.assertNotNull(car.getOwnerId());
    }
}
