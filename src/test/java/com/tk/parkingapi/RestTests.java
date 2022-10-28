package com.tk.parkingapi;

import com.tk.parkingapi.containers.CleanDatabaseContainer;
import com.tk.parkingapi.dto.VehicleDTO;
import com.tk.parkingapi.factory.VehicleFactory;
import com.tk.parkingapi.mapper.ParkingDTOMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
                .given().auth().basic("admin", "admin")
                .when().get("/space")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenNoEmptySpacesShouldThrowException(){
        testUtils.parkOnAllSpaces();

        RestAssured
                .given().auth().basic("admin", "admin")
                .when().get("/space/empty")
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

        RestAssured
                    .given().auth().basic("admin", "admin")
                        .when().contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(vehicleDTO).put("/")
                        .then().statusCode(HttpStatus.NOT_FOUND.value());


        testUtils.unParkAllVehicles();
    }

    @Test
    public void whenParkingAtAFullSpaceShouldThrowException(){
        // TODO: Explain why calling this on the methods and not using @BeforeAll and @AfterAll annotations
        testUtils.parkOnAllSpaces();

        val vehicleDTO = getVehicleDTO();

        RestAssured
                        .given().auth().basic("admin", "admin")
                        .when().contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(vehicleDTO).put("/1")
                        .then().statusCode(HttpStatus.CONFLICT.value());

        testUtils.unParkAllVehicles();
    }

    @Test
    public void whenUnParkingOnOccupiedSpaceThenOk(){
        testUtils.parkOnAllSpaces();

            RestAssured
                            .given().auth().basic("admin", "admin")
                            .when().delete("/1")
                            .then().statusCode(HttpStatus.OK.value());

        testUtils.unParkAllVehicles();
    }

    @Test
    public void whenUnParkingOnEmptySpaceThenNotFound(){
        RestAssured
                .given().auth().basic("admin", "admin")
                .when().delete("/1")
                .then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenUnParkingNonParkedVehicleByPlateThenNotFound(){
        val vehiclePlate = factory.build().getPlate();

        RestAssured
                .given().auth().basic("admin", "admin")
                .when().delete("/vehicle/" + vehiclePlate)
                .then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    // TODO: Maybe use OpenFeign to consume endpoints?
    @Test
    public void whenUnParkingParkedVehicleByPlateThenOk(){
        val vehicleDTO = getVehicleDTO();

        RestAssured
                .given().auth().basic("admin", "admin")
                .when().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(vehicleDTO).put("/1")
                .then().statusCode(HttpStatus.OK.value());

        RestAssured
                .given().auth().basic("admin", "admin")
                .when().delete("/vehicle/" + vehicleDTO.getPlate())
                .then().statusCode(HttpStatus.OK.value());
    }

    private VehicleDTO getVehicleDTO(){
        val vehicle = factory.build();
        val vehicleDTO = ParkingDTOMapper.vehicleToDTO(vehicle);

        return vehicleDTO;
    }
}
