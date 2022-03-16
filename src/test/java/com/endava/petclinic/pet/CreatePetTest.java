package com.endava.petclinic.pet;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class CreatePetTest extends TestBaseClass {

    @Test
    public void shouldCreatePet() {
        //Given
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        long id = createOwnerResponse.body().jsonPath().getLong("id");
        owner.setId(id);


        PetType petType = new PetType();
        petType.setId(1L);
        Pet pet = testDataProvider.getPet(owner, petType);
        //When
        Response response = petClient.createPet(pet);
        //Then
        response.then().statusCode(HttpStatus.SC_CREATED);

    }
}
