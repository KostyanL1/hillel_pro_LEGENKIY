package org.legenkiy.lesson42.service;

import lombok.RequiredArgsConstructor;
import org.legenkiy.lesson42.model.Post;
import org.legenkiy.lesson42.model.User;
import org.legenkiy.lesson42.repository.PostRepository;
import org.legenkiy.lesson42.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsersByEmailDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    @Transactional
    public User createUserWithPosts(User user, List<Post> posts) {
        User savedUser = userRepository.save(user);
        for (Post post : posts) {
            post.setUser(savedUser);
            postRepository.save(post);
        }

        return savedUser;
    }
}
