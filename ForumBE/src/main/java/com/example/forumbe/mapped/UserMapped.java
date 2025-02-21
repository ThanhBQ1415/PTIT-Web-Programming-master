package com.example.forumbe.mapped;

import com.example.forumbe.dto.UserDTO;
import com.example.forumbe.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapped {
    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAboutMe(user.getAboutMe());
        userDTO.setActive(user.isActive());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAboutMe(userDTO.getAboutMe());
        user.setAvatar(userDTO.getAvatar());
        user.setActive(userDTO.isActive());
        user.setRoles(userDTO.getRoles());
        return user;
    }
}
