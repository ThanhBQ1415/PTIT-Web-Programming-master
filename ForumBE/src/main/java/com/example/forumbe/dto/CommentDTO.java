package com.example.forumbe.dto;

import com.example.forumbe.entity.Comment;
import com.example.forumbe.service.PostService;
import com.example.forumbe.service.impl.PostServiceImpl;
import com.example.forumbe.service.impl.UserServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String body;
    private Long postId;
    private Long userId;
    private String username;
    private Date createdAt;
    private List<CommentVoteDTO> commentVotes;

    public CommentDTO() {
    }
}
