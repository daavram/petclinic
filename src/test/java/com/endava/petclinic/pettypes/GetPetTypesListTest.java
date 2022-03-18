package com.endava.petclinic.pettypes;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetPetTypesListTest extends TestBaseClass {
    @Test
    public void shouldGetPetTypesList() {
        //GIVEN
        PetType petType = testDataProvider.getPetTypes();
        Response createPetTypesResponse = petTypeClient.createPetType(petType);
        createPetTypesResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long petTypeID = createPetTypesResponse.body().jsonPath().getLong("id");

        //WHEN
        Response response = petTypeClient.getPetTypesList();

        //THEN
//        // first validation model
        response.then().statusCode(HttpStatus.SC_OK)
                .body("find{it->it.id==%s}.name", withArgs(petTypeID), is(petType.getName()));
//
        // second validation model
        PetType actualPetType = response.body().jsonPath().param("id", petTypeID).getObject("find{it -> it.id==id}", PetType.class);
        assertThat(actualPetType, is(petType));

        // third validation model
        List<PetType> petTypeList = response.body().jsonPath().getList("", PetType.class);
        assertThat(petTypeList, hasItem(petType));
    }

}
