package org.legenkiy.lesson42.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.legenkiy.lesson42.model.Post;
import org.legenkiy.lesson42.model.User;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserWithPostsRequest {
    private User user;
    private List<Post> posts;

}
