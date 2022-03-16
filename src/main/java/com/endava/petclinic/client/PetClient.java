package com.endava.petclinic.client;

import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import com.endava.petclinic.model.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static util.EnvReader.*;

public class PetClient {

    public Response createPet(Pet pet) {
        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(getBaseUri())
                .port(getPort())
                .basePath(getBasePath())
                .contentType(ContentType.JSON)
                .body(pet)
                .post("/api/pets");
    }
}
