package com.endava.petclinic.client;

import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class OwnerClient extends BaseClient {

    public Response createOwner(Owner owner) {

        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("/api/owners");

    }

    public Response getOwnerByID(Long ownerId) {

        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .get("/api/owners/{ownerId}");
    }

    public Response getOwnerList() {

        return getBasicRestConfig()
                .get("/api/owners");
    }

    public Response deleteOwnerByID(Long ownerId) {
        return getBasicRestConfig()
                .pathParam("ownerID", ownerId)
                .delete("/api/owners/{ownerID}");
    }

    public Response updateOwnerByID(Long ownerId, Owner owner) {
        return getBasicRestConfig()
                .body(owner)
                .contentType(ContentType.JSON)
                .pathParam("ownerID", ownerId)
                .put("/api/owners/{ownerID}");
    }
}
