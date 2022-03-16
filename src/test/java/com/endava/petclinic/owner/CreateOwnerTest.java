package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOwnerTest extends TestBaseClass {

    @Test
    public void shouldCreateOwner() {

        //GIVEN
        Owner owner = testDataProvider.getOwner();

        //WHEN
        Response response = ownerClient.createOwner(owner);

        // THEN
        response.then().statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));
    }

    @Test
    public void shouldFailCreateOwnerGivenEmptyFirstName() {

        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setFirstName("");
        //WHEN
        Response response = ownerClient.createOwner(owner);

        // THEN
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailCreateOwnerGivenNullFirstName() {

        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setFirstName(null);

        //WHEN
        Response response = ownerClient.createOwner(owner);

        // THEN
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailCreateOwnerGivenFewDigitsTelephone() {

        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(0, 0));

        //WHEN
        Response response = ownerClient.createOwner(owner);

        // THEN
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void shouldFailCreateOwnerGivenManyDigitsTelephone() {

        //GIVEN
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(11, 100));

        //WHEN
        Response response = ownerClient.createOwner(owner);

        // THEN
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
