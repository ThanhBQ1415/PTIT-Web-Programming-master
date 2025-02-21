package com.example.forumbe.controller;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.PostDTO;
import com.example.forumbe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public DataResponse<List<PostDTO>> getAll() {
        return postService.getAll();
    }

    @GetMapping("/post/{id}")
    public PostDTO getById(@PathVariable(value = "id") Long id) {
        return postService.getById(id);
    }

    @GetMapping("/post-by-user/{id}")
    public DataResponse<List<PostDTO>> getByUserId(@PathVariable(value = "id") Long id) {
        return postService.getByUserId(id);
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PostDTO create(@RequestBody PostDTO request) {
        request.setPostVotes(new ArrayList<>());
        request.setCreatedAt(new Date());
        return postService.save(request);
    }

    @DeleteMapping("post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteById(@PathVariable(value = "id") Long id) {
        postService.deleteById(id);
    }

    @PutMapping("post/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PostDTO updateById(@PathVariable(value = "id") Long id, @RequestBody PostDTO request) {
        PostDTO postDTO = postService.getById(id);
        postDTO.setTitle(request.getTitle());
        postDTO.setBody(request.getBody());
        postDTO.setUpdatedAt(new Date());
        postDTO.setOpen(request.isOpen());
        return postService.save(postDTO);
    }
}
