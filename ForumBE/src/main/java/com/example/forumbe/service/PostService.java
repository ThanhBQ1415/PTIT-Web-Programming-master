package com.example.forumbe.service;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostDTO;
import com.example.forumbe.entity.Post;

import java.util.List;

public interface PostService {
    DataResponse<List<PostDTO>> getAll();

    DataResponse<List<PostDTO>> getByUserId(Long id);

    PostDTO getById(Long id);

    PostDTO save(PostDTO postDTO);

    void deleteById(Long id);
}
