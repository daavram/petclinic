package com.endava.petclinic.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true)
class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String telephone;

    Owner() {
    }

    Owner(String firstName, String lastName, String city, String address, String telephone) {
        this.firstName = firstName
        this.lastName = lastName
        this.city = city
        this.address = address
        this.telephone = telephone
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getFirstName() {
        return firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        return lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getCity() {
        return city
    }

    void setCity(String city) {
        this.city = city
    }

    String getAddress() {
        return address
    }

    void setAddress(String address) {
        this.address = address
    }

    String getTelephone() {
        return telephone
    }

    void setTelephone(String telephone) {
        this.telephone = telephone
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Owner owner = (Owner) o

        if (address != owner.address) return false
        if (city != owner.city) return false
        if (firstName != owner.firstName) return false
        if (lastName != owner.lastName) return false
        if (telephone != owner.telephone) return false

        return true
    }

    int hashCode() {
        int result
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0)
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0)
        result = 31 * result + (city != null ? city.hashCode() : 0)
        result = 31 * result + (address != null ? address.hashCode() : 0)
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0)
        return result
    }


    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
