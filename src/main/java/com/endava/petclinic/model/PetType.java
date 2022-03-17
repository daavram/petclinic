package com.endava.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PetType {

    private Long id;
    private String name;

    @Override
    public String toString() {
        return "PetType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
