package com.example.forumbe.service.impl;

import com.example.forumbe.dto.CommentVoteDTO;
import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.entity.Comment;
import com.example.forumbe.entity.CommentVote;
import com.example.forumbe.entity.User;
import com.example.forumbe.mapped.CommentVoteMapped;
import com.example.forumbe.repository.CommentRepository;
import com.example.forumbe.repository.CommentVoteRepository;
import com.example.forumbe.repository.UserRepository;
import com.example.forumbe.service.CommentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentVoteServiceImpl implements CommentVoteService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentVoteRepository commentVoteRepository;

    @Override
    public CommentVoteDTO save(CommentVoteDTO commentVoteDTO) {
        CommentVote commentVote = CommentVoteMapped.convertToEntity(commentVoteDTO);
        return CommentVoteMapped.convertToDTO(commentVoteRepository.save(commentVote));
    }

    @Override
    public CommentVoteDTO getById(Long id) {
        return CommentVoteMapped.convertToDTO(commentVoteRepository.findById(id).get());
    }

    @Override
    public DataResponse<List<CommentVoteDTO>> getByCommentId(Long commentId) {
        DataResponse dataResponse = new DataResponse<>();
        List<CommentVoteDTO> commentVoteDTOS = commentVoteRepository.findCommentVoteByComment(commentRepository.findById(commentId).get()).stream().map(item -> CommentVoteMapped.convertToDTO(item)).collect(Collectors.toList());
        dataResponse.setData(commentVoteDTOS);
        dataResponse.setTotalItems(commentVoteDTOS.size());
        return dataResponse;
    }

    @Override
    public void deleteByUserIdAndCommentId(Long userId, Long commentId) {
        User user = userRepository.findById(userId).get();
        Comment comment = commentRepository.findById(commentId).get();
        commentVoteRepository.deleteByUserAndComment(user, comment);
    }

    @Override
    public CommentVoteDTO getByUserIdAndCommentId(Long userId, Long commentId) {
        User user = userRepository.findById(userId).get();
        Comment comment = commentRepository.findById(commentId).get();
        return CommentVoteMapped.convertToDTO(commentVoteRepository.findCommentVoteByUserAndComment(user, comment));
    }
}
