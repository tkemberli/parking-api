package com.tk.parkingapi;

import com.tk.parkingapi.entity.Car;
import com.tk.parkingapi.factory.CarFactory;
import lombok.val;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarFactoryTests {

    private CarFactory factory;

    @BeforeEach
    private void initialize(){
        factory = new CarFactory();
    }

    @Test
    public void shouldBuildWithAllProperties(){
        val car = factory.buildCar();
        Assert.assertNotNull(car.getId());
        Assert.assertNotNull(car.getPlate());
        Assert.assertNotNull(car.getModel());
        Assert.assertNotNull(car.getColor());
        Assert.assertNotNull(car.getOwnerId());
    }
}
