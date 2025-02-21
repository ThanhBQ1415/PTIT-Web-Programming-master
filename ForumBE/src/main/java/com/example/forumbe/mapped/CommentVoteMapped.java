package com.example.forumbe.mapped;

import com.example.forumbe.dto.CommentVoteDTO;
import com.example.forumbe.entity.CommentVote;
import com.example.forumbe.service.CommentService;
import com.example.forumbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentVoteMapped {
    static UserService userService;
    static CommentService commentService;

    @Autowired
    public CommentVoteMapped(UserService userService, CommentService commentService) {
        CommentVoteMapped.userService = userService;
        CommentVoteMapped.commentService = commentService;
    }

    public static CommentVote convertToEntity(CommentVoteDTO commentVoteDTO) {
        CommentVote commentVote = new CommentVote();
        commentVote.setId(commentVoteDTO.getId());
        commentVote.setUpVote(commentVoteDTO.isUpVote());
        System.out.println(commentVoteDTO.getUserId());
        commentVote.setUser(UserMapped.convertToEntity(userService.getById(commentVoteDTO.getUserId())));
        commentVote.setComment(commentService.getById(commentVoteDTO.getCommentId()));
        commentVote.setUpdatedAt(commentVoteDTO.getUpdatedAt());
        return commentVote;
    }

    public static CommentVoteDTO convertToDTO(CommentVote commentVote) {
        CommentVoteDTO commentVoteDTO = new CommentVoteDTO();
        commentVoteDTO.setId(commentVote.getId());
        commentVoteDTO.setUserId(commentVote.getUser().getId());
        commentVoteDTO.setCommentId(commentVote.getComment().getId());
        commentVoteDTO.setUpVote(commentVote.isUpVote());
        commentVoteDTO.setUpdatedAt(commentVote.getUpdatedAt());
        return commentVoteDTO;
    }
}
