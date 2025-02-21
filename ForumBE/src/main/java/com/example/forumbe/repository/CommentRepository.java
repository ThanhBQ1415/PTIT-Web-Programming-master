package com.example.forumbe.repository;

import com.example.forumbe.entity.Comment;
import com.example.forumbe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByPost(Post post);
}
