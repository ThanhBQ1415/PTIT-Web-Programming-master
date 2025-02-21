package com.example.forumbe.service;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostVoteDTO;

import java.util.List;

public interface PostVoteService {
    PostVoteDTO save(PostVoteDTO postVoteDTO);

    PostVoteDTO getById(Long id);

    DataResponse<List<PostVoteDTO>> getByPostId(Long postId);

    void deleteById(Long id);

    void deleteByPostIdAndUserId(Long postId, Long userId);

    PostVoteDTO getByPostIdAndUserId(Long postId, Long userId);
}
