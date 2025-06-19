package org.legenkiy.lesson42.controller;
import lombok.RequiredArgsConstructor;
import org.legenkiy.lesson42.dto.UserWithPostsRequest;
import org.legenkiy.lesson42.model.Post;
import org.legenkiy.lesson42.model.User;
import org.legenkiy.lesson42.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    @GetMapping("/by-domain")
    public ResponseEntity<List<User>> getUsersByEmailDomain(@RequestParam String domain) {
        return ResponseEntity.ok(userService.getUsersByEmailDomain(domain));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getPostsByUserId(userId));
    }

    @PostMapping("/with-posts")
    public ResponseEntity<User> createUserWithPosts(
            @RequestBody UserWithPostsRequest request) {
        return ResponseEntity.ok(
                userService.createUserWithPosts(request.getUser(), request.getPosts())
        );
}
