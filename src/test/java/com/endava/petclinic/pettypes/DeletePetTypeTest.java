package com.endava.petclinic.pettypes;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


public class DeletePetTypeTest extends TestBaseClass {

    @Test
    public void shouldDeletePetType() {
        //GIVEN
        PetType petType = testDataProvider.getPetTypes();
        Response createPetTypeResponse = petTypeClient.createPetType(petType);
        createPetTypeResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long petTypeID = createPetTypeResponse.body().jsonPath().getLong("id");

        //WHEN
        Response response = petTypeClient.deletePetTypeByID(petTypeID);

        //THEN
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
