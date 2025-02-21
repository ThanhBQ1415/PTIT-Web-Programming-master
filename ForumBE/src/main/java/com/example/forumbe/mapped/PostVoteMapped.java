package com.example.forumbe.mapped;

import com.example.forumbe.dto.PostVoteDTO;
import com.example.forumbe.entity.PostVote;
import com.example.forumbe.service.PostService;
import com.example.forumbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostVoteMapped {
    static PostService postService;
    static UserService userService;

    @Autowired
    public PostVoteMapped(PostService postService, UserService userService) {
        PostVoteMapped.postService = postService;
        PostVoteMapped.userService = userService;
    }

    public static PostVoteDTO convertToDTO(PostVote postVote) {
        PostVoteDTO postVoteDTO = new PostVoteDTO();
        postVoteDTO.setId(postVote.getId());
        postVoteDTO.setPostId(postVote.getPost().getId());
        postVoteDTO.setUserId(postVote.getUser().getId());
        postVoteDTO.setUpdatedAt(postVote.getUpdatedAt());
        postVoteDTO.setUpVote(postVote.isUpVote());
        return postVoteDTO;
    }

    public static PostVote convertToEntity(PostVoteDTO postVoteDTO) {
        PostVote postVote = new PostVote();
        postVote.setId(postVoteDTO.getId());
        postVote.setPost(PostMapped.convertToEntity(postService.getById(postVoteDTO.getPostId())));
        postVote.setUser(UserMapped.convertToEntity(userService.getById(postVoteDTO.getUserId())));
        postVote.setUpdatedAt(postVoteDTO.getUpdatedAt());
        postVote.setUpVote(postVoteDTO.isUpVote());
        return postVote;
    }
}
