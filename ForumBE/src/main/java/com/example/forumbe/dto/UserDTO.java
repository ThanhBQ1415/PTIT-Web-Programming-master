package com.example.forumbe.dto;

import com.example.forumbe.common.ERole;

import com.example.forumbe.entity.Role;
import com.example.forumbe.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String aboutMe;
    private String avatar;
    private boolean isActive;
    private Set<Role> roles = new HashSet<>();

    public UserDTO(){}
}