package com.example.links.utils.mapper;

import com.example.links.dto.CategoryDto;
import com.example.links.entity.Category;
import com.example.links.utils.TimeConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    TimeConverter timeConverter = new TimeConverter();

    public CategoryDto mapCategoryToCategoryDto(Category Category) {

        return CategoryDto.builder()
                .name(Category.getName())
                .createdAt(timeConverter.longToString(Category.getCreatedAt()))
                .build();
    }

    public List<CategoryDto> mapListCategoryToListCategoryDto(List<Category> list) {

        List<CategoryDto> CategoryDtoList = new ArrayList<>();
        for (Category c : list) {
            CategoryDtoList.add(mapCategoryToCategoryDto(c));
        }
        return CategoryDtoList;
    }

}
