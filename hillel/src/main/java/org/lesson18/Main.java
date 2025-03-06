package org.lesson18;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.createList();

        Optional<User> userById = userRepository.findUserById((int)((Math.random() * 5) + 1));

        userById.ifPresent(System.out::println);


        Optional<User> userByEmail = userRepository.findUserByEmail("olha@example.com");

        userByEmail.ifPresent(System.out::println);


        Optional<List<User>> optionalUserList = userRepository.findAllUsers();

        optionalUserList.ifPresent(users -> System.out.println("Кількість елементів списку: " + users.size()));


    }



}
