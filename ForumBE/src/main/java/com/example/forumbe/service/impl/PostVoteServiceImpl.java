package com.example.forumbe.service.impl;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostVoteDTO;
import com.example.forumbe.entity.Post;
import com.example.forumbe.entity.PostVote;
import com.example.forumbe.entity.User;
import com.example.forumbe.mapped.PostVoteMapped;
import com.example.forumbe.repository.PostRepository;
import com.example.forumbe.repository.PostVoteRepository;
import com.example.forumbe.repository.UserRepository;
import com.example.forumbe.service.PostVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostVoteServiceImpl implements PostVoteService {
    @Autowired
    PostVoteRepository postVoteRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public PostVoteDTO save(PostVoteDTO postVoteDTO) {
        PostVote postVote = PostVoteMapped.convertToEntity(postVoteDTO);
        return PostVoteMapped.convertToDTO(postVoteRepository.save(postVote));
    }

    @Override
    public PostVoteDTO getById(Long id) {
        return PostVoteMapped.convertToDTO(postVoteRepository.findById(id).get());
    }

    @Override
    public DataResponse<List<PostVoteDTO>> getByPostId(Long postId) {
        DataResponse dataResponse = new DataResponse<>();
        List<PostVoteDTO> postVoteDTOS = postVoteRepository.findPostVoteByPost(postRepository.findById(postId).get()).stream().map(item -> PostVoteMapped.convertToDTO(item)).collect(Collectors.toList());
        dataResponse.setData(postVoteDTOS);
        dataResponse.setTotalItems(postVoteDTOS.size());
        return dataResponse;
    }

    @Override
    public void deleteById(Long id) {
        postVoteRepository.deleteById(id);
    }

    @Override
    public void deleteByPostIdAndUserId(Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();
        postVoteRepository.deleteByPostAndUser(post, user);
    }

    @Override
    public PostVoteDTO getByPostIdAndUserId(Long postId, Long userId) {
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();
        return PostVoteMapped.convertToDTO(postVoteRepository.findPostVoteByUserAndPost(user, post));
    }
}
