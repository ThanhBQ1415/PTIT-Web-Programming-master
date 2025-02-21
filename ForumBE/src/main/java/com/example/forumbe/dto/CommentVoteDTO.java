package com.example.forumbe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentVoteDTO {
    private Long id;
    private boolean isUpVote;
    private Date updatedAt;
    private Long userId;
    private Long commentId;

    public CommentVoteDTO() {
    }

}
