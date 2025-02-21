package com.example.forumbe.service;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.TagDTO;

import java.util.List;

public interface TagService {
    DataResponse<List<TagDTO>> getAll();

    TagDTO save(TagDTO tagDTO);
}
