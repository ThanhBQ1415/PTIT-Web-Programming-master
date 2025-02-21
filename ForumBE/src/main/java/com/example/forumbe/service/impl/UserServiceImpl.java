package com.example.forumbe.service.impl;

import com.example.forumbe.dto.UserDTO;
import com.example.forumbe.entity.User;
import com.example.forumbe.mapped.UserMapped;
import com.example.forumbe.repository.UserRepository;
import com.example.forumbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO getById(Long id) {
        return UserMapped.convertToDTO(userRepository.findById(id).get());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).get();
        user.setAvatar(userDTO.getAvatar());
        user.setAboutMe(userDTO.getAboutMe());
        return UserMapped.convertToDTO(userRepository.save(user));
    }
}
