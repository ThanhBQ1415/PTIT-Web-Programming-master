package com.example.forumbe.controller;

import com.example.forumbe.dto.UserDTO;
import com.example.forumbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public UserDTO getById(@PathVariable(value = "id") Long id) {
        return userService.getById(id);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserDTO update(@PathVariable(value = "id") Long id, @RequestBody UserDTO request) {
        request.setId(id);
        return userService.save(request);
    }
}
