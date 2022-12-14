package com.tk.parkingapi.util.factory;

import com.tk.parkingapi.ParkingApiApplication;
import com.tk.parkingapi.entity.Vehicle;
import lombok.val;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
* A factory that builds random Cars for testing purposes
 */

// TODO: Move the attribute types to a Enum?

@Component
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

    public Vehicle build(){

        String state = getRandomAttribute("car.states");
        String plate = state + "-" + getUUID().substring(0, 12);

        String model = getRandomAttribute("car.model");

        String color = getRandomAttribute("car.color");

        String ownerID = getUUID().substring(0, 17);

        LocalDateTime entryDate = LocalDateTime.now();

        return new Vehicle(
                plate,
                model,
                color,
                ownerID,
                entryDate,
                null,
                0.0d
        );
    }


    /**
     * Gets random attributes such as color or model from the attributes properties file
     * @param attribute car.state, car.model or car.color (view TODO above)
     * @return random attribute as String
     */
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
