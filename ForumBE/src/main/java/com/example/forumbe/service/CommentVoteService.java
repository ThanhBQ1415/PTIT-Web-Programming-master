package com.example.forumbe.service;

import com.example.forumbe.dto.CommentVoteDTO;
import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostVoteDTO;

import java.util.List;

public interface CommentVoteService {
    CommentVoteDTO save(CommentVoteDTO commentVoteDTO);

    CommentVoteDTO getById(Long id);

    DataResponse<List<CommentVoteDTO>> getByCommentId(Long commentId);

    void deleteByUserIdAndCommentId(Long userId, Long commentId);

    CommentVoteDTO getByUserIdAndCommentId(Long userId, Long commentId);
}
