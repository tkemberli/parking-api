package com.tk.parkingapi;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTests {

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
}
