package com.example.forumbe.service;

import com.example.forumbe.dto.UserDTO;

public interface UserService {
    UserDTO getById(Long id);
    UserDTO save(UserDTO userDTO);
}
