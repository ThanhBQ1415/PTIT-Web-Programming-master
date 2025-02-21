package com.example.forumbe.repository;

import com.example.forumbe.entity.Post;
import com.example.forumbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}