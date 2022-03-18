package com.endava.petclinic.pettypes;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class GetPetTypeByIdTest extends TestBaseClass {

    @Test
    public void shouldGetPetTypeById() {

        //GIVEN
        PetType petType = testDataProvider.getPetTypes();
        Response createPetTypesResponse = petTypeClient.createPetType(petType);
        createPetTypesResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long petTypeID = createPetTypesResponse.body().jsonPath().getLong("id");

        //WHEN
        Response getPetTypeByIDresponse = petTypeClient.getPetTypeByID(petTypeID);
        Long petTypeIDResponse = getPetTypeByIDresponse.body().jsonPath().getLong("id");
        System.out.println(petTypeIDResponse);

        //THEN
        // first validation model
        getPetTypeByIDresponse.then().statusCode(HttpStatus.SC_OK);

    }
}
