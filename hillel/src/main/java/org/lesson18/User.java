package org.lesson18;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private int id;
    private String name;
    private String email;

    @Override
    public String toString(){
        return "id: " + id +
                "\n" + "name: " + name +
                "\n" + "email: " + email + "\n";

    }

}
