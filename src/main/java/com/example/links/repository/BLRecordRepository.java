package com.example.links.repository;

import com.example.links.entity.BLRecord;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BLRecordRepository extends ListCrudRepository<BLRecord, Integer> {

    List<BLRecord> findByDomainContaining(String domain);

    @Modifying
    @Query("UPDATE \"black_list\" SET domain = :domain WHERE id = :id ")
    void updateById(
            @Param("id") Integer id,
            @Param("domain") String domain);
}
