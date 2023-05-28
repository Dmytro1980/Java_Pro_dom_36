package com.example.links.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "black_list")
public class BLRecord {

    @Id
    @Column("id")
    private Integer id;

    @Column("domain")
    private String domain;

    @Column("created_at")
    private Long createdAt;

    public BLRecord(String domain, Long dateAddBLRecord) {
        this.domain = domain;
        this.createdAt = dateAddBLRecord;
    }
}
