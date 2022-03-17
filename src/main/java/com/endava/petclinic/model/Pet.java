package com.endava.petclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pet {

    private Long id;
    private String name;
    private String birthDate;
    private PetType type;
    private Owner owner;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", type=" + type +
                ", owner=" + owner +
                '}';
    }
}
