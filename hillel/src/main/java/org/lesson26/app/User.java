package org.lesson26.app;


import lombok.Data;

@Data
public class User {
    private String name;
    private String surname;
    private Address address;

    public User (String name, String surname){
        this.name = name;
        this.surname = surname;
    }

}
