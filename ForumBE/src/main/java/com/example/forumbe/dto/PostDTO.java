package com.example.forumbe.dto;

import com.example.forumbe.entity.Post;
import com.example.forumbe.service.impl.UserServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String body;
    private Date createdAt;
    private Date updatedAt;
    private Date closedAt;
    private boolean isOpen;
    private Long userId;
    private String username;
    private List<TagDTO> tags;
    private List<PostVoteDTO> postVotes;

    public PostDTO() {
    }

}