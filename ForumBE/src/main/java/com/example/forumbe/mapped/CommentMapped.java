package com.example.forumbe.mapped;

import com.example.forumbe.dto.CommentDTO;
import com.example.forumbe.entity.Comment;
import com.example.forumbe.service.PostService;
import com.example.forumbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CommentMapped {
    static PostService postService;
    static UserServiceImpl userService;

    @Autowired
    public CommentMapped(PostService postService, UserServiceImpl userService) {
        CommentMapped.postService = postService;
        CommentMapped.userService = userService;
    }

    public static CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        commentDTO.setUserId(comment.getUser().getId());
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setUsername(comment.getUser().getUsername());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setCommentVotes(comment.getCommentVotes().stream().map(item -> CommentVoteMapped.convertToDTO(item)).collect(Collectors.toList()));
        return commentDTO;
    }

    public static Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setBody(commentDTO.getBody());
        comment.setPost(PostMapped.convertToEntity(postService.getById(commentDTO.getPostId())));
        comment.setUser(UserMapped.convertToEntity(userService.getById(commentDTO.getUserId())));
        comment.setCreatedAt(commentDTO.getCreatedAt());
        comment.setCommentVotes(commentDTO.getCommentVotes().stream().map(item -> CommentVoteMapped.convertToEntity(item)).collect(Collectors.toList()));
        return comment;
    }
}
