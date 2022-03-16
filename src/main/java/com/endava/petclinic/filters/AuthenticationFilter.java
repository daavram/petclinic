package com.endava.petclinic.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import util.EnvReader;

public class AuthenticationFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext context) {
        if (EnvReader.getAdminPassword() != null && EnvReader.getAdminUsername() != null) {
            requestSpec.auth().preemptive().basic(EnvReader.getAdminUsername(), EnvReader.getAdminPassword());
        }
        return context.next(requestSpec, responseSpec);
    }
}
