package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetOwnerListTest extends TestBaseClass {
    @Test
    public void shouldGetOwnerList() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long ownerID = createOwnerResponse.body().jsonPath().getLong("id");

        //WHEN
        Response response = ownerClient.getOwnerList();

        //THEN
        // first validation model
        response.then().statusCode(HttpStatus.SC_OK)
                .body("find{it->it.id==%s}.firstName", withArgs(ownerID), is(owner.getFirstName()));

        // second validation model
        Owner actualOwner = response.body().jsonPath().param("id", ownerID).getObject("find{it -> it.id==id}", Owner.class);
        assertThat(actualOwner, is(owner));

        // third validation model
        List<Owner> ownerList = response.body().jsonPath().getList("", Owner.class);
        assertThat(ownerList, hasItem(owner));
    }

}
