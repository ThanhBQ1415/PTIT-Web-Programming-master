package com.example.forumbe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostVoteDTO {
    private Long id;
    private boolean isUpVote;
    private Date updatedAt;
    private Long userId;
    private Long postId;

    public PostVoteDTO() {
    }
}
