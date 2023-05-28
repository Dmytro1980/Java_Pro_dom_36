package com.example.links.entity;

//import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "links")
public class Link {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("value")
    private String value;

    @Column("category_id")
    private Integer category;

    @Builder.Default
    @MappedCollection(idColumn = "link_id", keyColumn = "tag_id")
    private Set<TagRef> tagRefs = new HashSet<>();


    @Column("created_at")
    private Long createdAt;


    public Link(String name, String value, Integer category, Long dateAddLink) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.createdAt = dateAddLink;
    }

    public void addTag (Tag tag){
        this.tagRefs.add(new TagRef(tag.getId()));
    }

}
