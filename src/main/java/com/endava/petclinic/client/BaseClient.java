package com.endava.petclinic.client;

import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.EnvReader;

import static io.restassured.RestAssured.given;

public class BaseClient {
    protected RequestSpecification getBasicRestConfig() {
        return given().filters(new AuthenticationFilter(), new LogFilter()).baseUri(EnvReader.getBaseUri()).port(EnvReader.getPort()).basePath(EnvReader.getBasePath());
    }

    public Response deletePetTypeByID(Long petTypeId) {
        return getBasicRestConfig().pathParam("petTypeID", petTypeId).delete("/api/pettypes/{petTypeID}");
    }

}
