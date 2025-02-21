package com.example.forumbe.service;

import com.example.forumbe.dto.CommentDTO;
import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment getById(Long id);

    DataResponse<List<CommentDTO>> getAll();

    DataResponse<List<CommentDTO>> getByPost(Long id);

    CommentDTO save(CommentDTO commentDTO);
}
