package com.example.forumbe.controller;

import com.example.forumbe.dto.CommentVoteDTO;
import com.example.forumbe.entity.User;
import com.example.forumbe.service.impl.CommentServiceImpl;
import com.example.forumbe.service.impl.CommentVoteServiceImpl;
import com.example.forumbe.service.impl.PostVoteServiceImpl;
import com.example.forumbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentVoteController {
    @Autowired
    CommentVoteServiceImpl commentVoteService;

    @PostMapping("/comment-vote")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommentVoteDTO create(@RequestBody CommentVoteDTO request) {
        request.setUpdatedAt(new Date());
        return commentVoteService.save(request);
    }

    @PostMapping("/comment-vote/delete-by-user-and-comment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteByUserIdAndCommentId(@RequestBody CommentVoteDTO request) {
        commentVoteService.deleteByUserIdAndCommentId(request.getUserId(), request.getCommentId());
    }

    @PutMapping("/comment-vote/update-by-user-and-comment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommentVoteDTO updateByUserIdAndCommentId(@RequestBody CommentVoteDTO request) {
        CommentVoteDTO commentVoteDTO = commentVoteService.getByUserIdAndCommentId(request.getUserId(), request.getCommentId());
        commentVoteDTO.setUpVote(!commentVoteDTO.isUpVote());
        commentVoteDTO.setUpdatedAt(new Date());
        return commentVoteService.save(commentVoteDTO);
    }
}
