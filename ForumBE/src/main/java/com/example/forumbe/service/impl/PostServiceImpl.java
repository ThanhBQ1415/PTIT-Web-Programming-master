package com.example.forumbe.service.impl;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostDTO;
import com.example.forumbe.entity.Post;
import com.example.forumbe.mapped.PostMapped;
import com.example.forumbe.repository.PostRepository;
import com.example.forumbe.repository.UserRepository;
import com.example.forumbe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostDTO save(PostDTO postDTO) {
        Post post = PostMapped.convertToEntity(postDTO);
        return PostMapped.convertToDTO(postRepository.save(post));
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public DataResponse<List<PostDTO>> getAll() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = new ArrayList<>();
        DataResponse<List<PostDTO>> dataResponse = new DataResponse<>();
        for (Post post : posts) {
            postDTOS.add(PostMapped.convertToDTO(post));
        }
        dataResponse.setData(postDTOS);
        dataResponse.setTotalItems(postDTOS.size());
        return dataResponse;
    }

    @Override
    public DataResponse<List<PostDTO>> getByUserId(Long id) {
        DataResponse<List<PostDTO>> dataResponse = new DataResponse<>();
        List<PostDTO> postDTOS = postRepository.findByUser(userRepository.findById(id).get()).stream().map(item -> PostMapped.convertToDTO(item)).collect(Collectors.toList());
        dataResponse.setData(postDTOS);
        dataResponse.setTotalItems(postDTOS.size());
        return dataResponse;
    }

    @Override
    public PostDTO getById(Long id) {
        return PostMapped.convertToDTO(postRepository.findById(id).get());
    }
}
