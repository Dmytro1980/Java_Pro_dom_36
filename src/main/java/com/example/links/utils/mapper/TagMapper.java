package com.example.links.utils.mapper;

import com.example.links.dto.TagDto;
import com.example.links.entity.Tag;
import com.example.links.utils.TimeConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {
    TimeConverter timeConverter = new TimeConverter();

    public TagDto mapTagToTagDto(Tag tag) {

        return TagDto.builder()
                .name(tag.getName())
                .createdAt(timeConverter.longToString(tag.getCreatedAt()))
                .build();
    }

    public List<TagDto> mapListTagToListTagDto(List<Tag> list) {

        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag t : list) {
            tagDtoList.add(mapTagToTagDto(t));
        }
        return tagDtoList;
    }

}
