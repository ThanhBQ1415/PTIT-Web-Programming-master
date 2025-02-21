package com.example.forumbe.controller;

import com.example.forumbe.dto.CommentDTO;
import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public DataResponse<List<CommentDTO>> getALl() {
        return commentService.getAll();
    }

    @GetMapping("/comments-by-post/{id}")
    public DataResponse<List<CommentDTO>> getByPost(@PathVariable(value = "id") Long id) {
        return commentService.getByPost(id);
    }

    @PostMapping("/comment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public CommentDTO create(@RequestBody CommentDTO request) {
        request.setCreatedAt(new Date());
        return commentService.save(request);
    }
}
