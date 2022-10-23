package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.dto.VehicleDTO;
import com.tk.parkingapi.factory.VehicleFactory;
import com.tk.parkingapi.mapper.ParkingDTOMapper;
import io.restassured.RestAssured;
import lombok.val;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTests extends CleanDatabaseContainer {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private VehicleFactory factory;

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUp(){
        RestAssured.port = randomPort;
    }


    @Test
    public void whenFindAllThenOk(){
        RestAssured
                .when().get("/")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenNoEmptySpacesShouldThrowException(){
        testUtils.parkOnAllSpaces();

        RestAssured
                .when().get("/empty")
                .then().statusCode(HttpStatus.NOT_FOUND.value())
                .body("httpStatus", Matchers.notNullValue())
                .body("message", Matchers.notNullValue())
                .body("timeStamp", Matchers.notNullValue());

        testUtils.unParkAllVehicles();
    }

    @Test
    public void whenParkingWithoutEmptySpacesShouldThrowException(){
        testUtils.parkOnAllSpaces();

        val vehicleDTO = getVehicleDTO();

        RestAssured.given()
                        .when().contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(vehicleDTO).put("/park")
                        .then().statusCode(HttpStatus.NOT_FOUND.value());


        testUtils.unParkAllVehicles();
    }

    @Test
    public void whenParkingAtAFullSpaceShouldThrowException(){
        testUtils.parkOnAllSpaces();

        val vehicleDTO = getVehicleDTO();

        RestAssured.given()
                        .when().contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(vehicleDTO).put("/park/1")
                        .then().statusCode(HttpStatus.CONFLICT.value());

        testUtils.unParkAllVehicles();
    }

    private VehicleDTO getVehicleDTO(){
        val vehicle = factory.build();
        val vehicleDTO = ParkingDTOMapper.vehicleToDTO(vehicle);

        return vehicleDTO;
    }
}
