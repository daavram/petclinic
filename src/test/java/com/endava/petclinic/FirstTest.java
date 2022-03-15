package com.endava.petclinic;

import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FirstTest {

    @Test
    public void firstTest() {
        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .log().all()
                .when()
                .get("/api/owners")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createOwner() {

        //Given
        Owner owner = new Owner("Mitica", "Dragomir", "Brutarie", "Jilava", "0235830990");
        System.out.println(owner);

        //When
        Response response = given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
                .when()
                .post("/api/owners")
                .prettyPeek();

        //Then
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("firstName", is(owner.getFirstName()))
                .body("lastName", is(owner.getLastName()))
                .body("address", is(owner.getAddress()))
                .body("telephone", is(owner.getTelephone()))
                .body("telephone", is(owner.getTelephone()))
                .body("city", is(owner.getCity()));
        //.body("pet", empty());

        Owner actualOwner = response.as(Owner.class);
        assertThat(actualOwner, is(owner));
    }

    @Test
    public void getOwnerByID() {
        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .pathParam("ownerID", 5)
                .log().all()
                .when()
                .get("/api/owners/{ownerID}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deleteOwnerByID() {
        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .pathParam("ownerID", 5)
                .log().all()
                .when()
                .delete("/api/owners/{ownerID}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
    @Test
    public void secondTest() {
        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .log().all()
                .when()
                .get("/api/pets")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

}
