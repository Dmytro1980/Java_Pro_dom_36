package com.example.links.repository;

import com.example.links.entity.Category;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {

    List<Category> findByNameContaining(String name);

    @Modifying
    @Query("UPDATE \"categories\" SET name = :name WHERE id = :id ")
    void updateById(
            @Param("id") Integer id,
            @Param("name") String name);
}
