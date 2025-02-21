package com.example.forumbe.controller;

import com.example.forumbe.dto.DataResponse;
import com.example.forumbe.dto.TagDTO;
import com.example.forumbe.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public DataResponse<List<TagDTO>> getAll() {
        return tagService.getAll();
    }

    @PostMapping("/tag")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public TagDTO create(@RequestBody TagDTO request) {
        return tagService.save(request);
    }
}
