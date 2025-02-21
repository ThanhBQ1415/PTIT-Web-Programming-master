package com.example.forumbe.controller;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostVoteDTO;
import com.example.forumbe.service.PostVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PostVoteController {
    @Autowired
    PostVoteService postVoteService;

    @PostMapping("/vote")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PostVoteDTO create(@RequestBody PostVoteDTO postVoteDTO) {
        postVoteDTO.setUpdatedAt(new Date());
        return postVoteService.save(postVoteDTO);
    }

    @GetMapping("/vote-by-post/{id}")
    public DataResponse<List<PostVoteDTO>> getByPost(@PathVariable(value = "id") Long id) {
        return postVoteService.getByPostId(id);
    }

    @PostMapping("/vote/delete-by-post-and-user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteByPostIdAndUserId(@RequestBody PostVoteDTO request) {
        postVoteService.deleteByPostIdAndUserId(request.getPostId(), request.getUserId());
    }

    @PutMapping("/vote/update-by-post-and-user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PostVoteDTO updateByPostIdAndUserId(@RequestBody PostVoteDTO request) {
        PostVoteDTO postVoteDTO = postVoteService.getByPostIdAndUserId(request.getPostId(), request.getUserId());
        postVoteDTO.setUpVote(!postVoteDTO.isUpVote());
        postVoteDTO.setUpdatedAt(new Date());
        return postVoteService.save(postVoteDTO);
    }

    @GetMapping("/vote/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PostVoteDTO getById(@PathVariable(value = "id") Long id) {
        return postVoteService.getById(id);
    }

}
