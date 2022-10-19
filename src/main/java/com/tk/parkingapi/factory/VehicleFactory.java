package com.tk.parkingapi.factory;

import com.tk.parkingapi.ParkingApiApplication;
import com.tk.parkingapi.entity.Vehicle;
import lombok.val;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
* A factory that builds random Cars for testing purposes
 */

public class VehicleFactory {

    private final Properties attributes;

    public VehicleFactory(){
        this.attributes = new Properties();

        val propertiesFilePath = "/car.attributes.properties";
        val propertiesFileStream =
                ParkingApiApplication.class.getResourceAsStream(propertiesFilePath);

        try{
            attributes.load(propertiesFileStream);
        }catch (IOException ioException) {
            val errorMessage = String.format("Could not read the properties file %s . Will be unable to build random cars",
                    propertiesFilePath);

            System.out.println(errorMessage);
            ioException.printStackTrace();
        }



    }

    public Vehicle buildCar(){
        String id = getUUID();

        String state = getRandomAttribute("car.states");
        String plate = state + "-" + getUUID().substring(0, 12);

        String model = getRandomAttribute("car.model");

        String color = getRandomAttribute("car.color");

        String ownerID = getUUID().substring(0, 17);

        return new Vehicle(
                id,
                plate,
                model,
                color,
                ownerID
        );
    }


    private String getRandomAttribute(String attribute){
        val attributes = this.attributes.getProperty(attribute).split(",");
        val attributesQty = attributes.length;
        val randomIndex = ThreadLocalRandom.current().nextInt(attributesQty);

        return attributes[randomIndex];
    }

    private String getUUID(){
        return UUID.randomUUID().toString();
    }
}
