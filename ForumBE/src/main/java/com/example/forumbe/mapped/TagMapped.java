package com.example.forumbe.mapped;

import com.example.forumbe.dto.TagDTO;
import com.example.forumbe.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapped {
    public static TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        return tagDTO;
    }

    public static Tag converToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        return tag;
    }
}
