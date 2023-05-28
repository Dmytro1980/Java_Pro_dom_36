package com.example.links.web;

import com.example.links.utils.mapper.CategoryMapper;
import com.example.links.dto.CategoryDto;
import com.example.links.entity.Category;
import com.example.links.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    // /category/all - find all categories
    @GetMapping("/all")
    public List<CategoryDto> findAll() {
        return this.categoryMapper.mapListCategoryToListCategoryDto(categoryService.findALL());
    }

    //  /category/find_by_name?text={text} - find by name
    @GetMapping("/find_by_name")
    public List<CategoryDto> findByName(@RequestParam("text") String text) {
        return this.categoryMapper.mapListCategoryToListCategoryDto(categoryService.findByName(text));
    }

    //    "/category/add?name={name} - add new category
    // добавление в Categories новой категории
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Category add(@RequestParam("name") String name) {

        Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();

        return categoryService.save(new Category(name, dateAddNote));
    }

    //  /category/update?id={id}?name={name} - update category by id
    @GetMapping("/update")
    public void updateById(@RequestParam("id") Integer id,
                           @RequestParam("name") String name) {
        categoryService.update(id, name);
    }

    //  /category/delete?category_id={category_id} - delete category by id
    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        categoryService.deleteById(id);
    }
}
