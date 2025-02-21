package com.example.forumbe.service.impl;

import com.example.forumbe.dto.CommentDTO;
import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.entity.Comment;
import com.example.forumbe.mapped.CommentMapped;
import com.example.forumbe.repository.CommentRepository;
import com.example.forumbe.repository.PostRepository;
import com.example.forumbe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public DataResponse<List<CommentDTO>> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        DataResponse<List<CommentDTO>> dataResponse = new DataResponse<>();
        for (Comment comment : comments) {
            commentDTOS.add(CommentMapped.convertToDTO(comment));
        }
        dataResponse.setData(commentDTOS);
        dataResponse.setTotalItems(commentDTOS.size());
        return dataResponse;
    }

    @Override
    public DataResponse<List<CommentDTO>> getByPost(Long id) {
        List<Comment> comments = commentRepository.findCommentByPost(postRepository.findById(id).get());
        List<CommentDTO> commentDTOS = new ArrayList<>();
        DataResponse<List<CommentDTO>> dataResponse = new DataResponse<>();
        for (Comment comment : comments) {
            commentDTOS.add(CommentMapped.convertToDTO(comment));
        }
        dataResponse.setData(commentDTOS);
        dataResponse.setTotalItems(commentDTOS.size());
        return dataResponse;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = CommentMapped.convertToEntity(commentDTO);
        return CommentMapped.convertToDTO(commentRepository.save(comment));
    }
}
