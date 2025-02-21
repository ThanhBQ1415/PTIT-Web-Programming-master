package com.example.forumbe.repository;


import com.example.forumbe.entity.Comment;
import com.example.forumbe.entity.CommentVote;
import com.example.forumbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentVoteRepository extends JpaRepository<CommentVote, Long> {
    List<CommentVote> findCommentVoteByComment(Comment comment);

    void deleteByUserAndComment(User user, Comment comment);

    CommentVote findCommentVoteByUserAndComment(User user, Comment comment);
}
