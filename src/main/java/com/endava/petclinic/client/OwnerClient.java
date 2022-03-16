package com.endava.petclinic.client;

import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static util.EnvReader.*;

public class OwnerClient {

    public Response createOwner(Owner owner) {

        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(getBaseUri())
                .port(getPort())
                .basePath(getBasePath())
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/api/owners");

    }

    public Response getOwnerByID(Long ownerId) {

        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(getBaseUri())
                .port(getPort())
                .basePath(getBasePath())
                .pathParam("ownerId", ownerId)
                .get("/api/owners/{ownerId}");
    }

    public Response getOwnerList() {

        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(getBaseUri())
                .port(getPort())
                .basePath(getBasePath())
                .get("/api/owners");
    }

    public Response deleteOwnerByID(Long ownerId) {
        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(getBaseUri())
                .port(getPort())
                .basePath(getBasePath())
                .pathParam("ownerID", ownerId)
                .delete("/api/owners/{ownerID}");
    }

    public Response updateOwnerByID(Long ownerId, Owner owner) {
        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(getBaseUri())
                .port(getPort())
                .basePath(getBasePath())
                .body(owner)
                .contentType(ContentType.JSON)
                .pathParam("ownerID", ownerId)
                .put("/api/owners/{ownerID}");
    }
}
