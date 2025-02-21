package com.example.forumbe.mapped;

import com.example.forumbe.dto.PostDTO;
import com.example.forumbe.entity.Post;
import com.example.forumbe.service.UserService;
import com.example.forumbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostMapped {
    private static UserService userService;

    @Autowired
    public PostMapped(UserService userService) {
        PostMapped.userService = userService;
    }

    public static Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setCreatedAt(postDTO.getCreatedAt());
        post.setUpdatedAt(postDTO.getUpdatedAt());
        post.setClosedAt(postDTO.getClosedAt());
        post.setOpen(postDTO.isOpen());
        post.setUser(UserMapped.convertToEntity(userService.getById(postDTO.getUserId())));
        return post;
    }

    public static PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setCreatedAt(post.getCreatedAt());
        postDTO.setUpdatedAt(post.getUpdatedAt());
        postDTO.setClosedAt(post.getClosedAt());
        postDTO.setOpen(post.isOpen());
        postDTO.setUserId(post.getUser().getId());
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setTags(post.getTags().stream().map(item -> TagMapped.convertToDTO(item)).collect(Collectors.toList()));
        postDTO.setPostVotes(post.getPostVotes().stream().map(item -> PostVoteMapped.convertToDTO(item)).collect(Collectors.toList()));
        return postDTO;
    }
}
