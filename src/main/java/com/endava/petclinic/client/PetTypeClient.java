package com.endava.petclinic.client;

import com.endava.petclinic.model.PetType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PetTypeClient extends BaseClient {

    public Response createPetType(PetType petType) {

        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(petType)
                .post("/api/pettypes");

    }

    public Response getPetTypesList() {

        return getBasicRestConfig()
                .get("/api/pettypes");
    }

    public Response getPetTypeByID(Long petTypeID) {

        return getBasicRestConfig()
                .pathParam("petTypeId", petTypeID)
                .get("/api/pettypes/{petTypeId}");
    }

}
