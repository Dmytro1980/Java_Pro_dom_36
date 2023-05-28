package com.example.links.utils.mapper;

import com.example.links.dto.LinkDto;
import com.example.links.entity.Link;
import com.example.links.entity.Tag;
import com.example.links.entity.TagRef;
import com.example.links.service.TagService;
import com.example.links.utils.TimeConverter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LinkMapper {
    TimeConverter timeConverter = new TimeConverter();

    private final TagService tagService;

    public LinkMapper(TagService tagService) {
        this.tagService = tagService;
    }

    public LinkDto mapLinkToLinkDto(Link link) {

        return LinkDto.builder()
                .name(link.getName())
                .value(link.getValue())
                .category(link.getCategory())
//                .tags(link.getTags())
                .tags(convertTagsToTagRefs(link.getTagRefs()))
//                .tagRefs(link.getTagRefs())
                .createdAt(timeConverter.longToString(link.getCreatedAt()))
                .build();
    }

    public List<LinkDto> mapListLinkToListLinkDto(List<Link> list) {

        List<LinkDto> linkDtoList = new ArrayList<>();
        for (Link l : list) {
            linkDtoList.add(mapLinkToLinkDto(l));
        }
        return linkDtoList;
    }

    private Set<Tag> convertTagsToTagRefs(Set<TagRef> tagRefs){

        Set<Tag> tags = new HashSet<>();

        for(TagRef tr: tagRefs){
            Integer id = tr.getId();
            Optional<Tag> tag = tagService.findByID(id);
            tags.add(tag.orElseThrow(() -> new IllegalArgumentException("no tags")));
        }
        return tags;
    }

}
