package com.example.forumbe.repository;

import com.example.forumbe.entity.Post;
import com.example.forumbe.entity.PostVote;
import com.example.forumbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostVoteRepository extends JpaRepository<PostVote, Long> {
    List<PostVote> findPostVoteByPost(Post post);

    void deleteByPostAndUser(Post post, User user);

    PostVote findPostVoteByUserAndPost(User user, Post post);
}
