package org.lesson18;

import java.util.*;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void createList(){
        this.users = new ArrayList<>(List.of(
                new User(1, "Олександр", "olexandr@example.com"),
                new User(2, "Марія", "maria@example.com"),
                new User(3, "Іван", "ivan@example.com"),
                new User(4, "Ольга", "olha@example.com"),
                new User(5, "Андрій", "andriy@example.com")
        )) ;
    }

    public Optional<User> findUserById(int id){
        return users.stream()
                .filter((user -> user.getId() == id))
                .findFirst();
    }
    public Optional<User>  findUserByEmail(String email){
        return users.stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }
    public Optional<List<User>>  findAllUsers(){
        return Optional.of(users);
    }


}
