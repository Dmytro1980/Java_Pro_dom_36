package com.example.links.dto;

import com.example.links.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LinkDto {


    private String name;

    private String value;

    private Integer category;

    private Set<Tag> tags;

    private String createdAt;


}
