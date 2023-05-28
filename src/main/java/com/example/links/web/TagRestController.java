package com.example.links.web;

import com.example.links.dto.TagDto;
import com.example.links.service.TagService;
import com.example.links.utils.mapper.TagMapper;
import com.example.links.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagRestController {

    private final TagService tagService;

    private final TagMapper tagMapper;

    // /tag/all - find all tags
    @GetMapping("/all")
    public List<TagDto> findAll() {
        return this.tagMapper.mapListTagToListTagDto(tagService.findALL());
    }

    //  /tag/find_by_name?text={text} - find by name
    @GetMapping("/find_by_name")
    public List<TagDto> findByName(@RequestParam("text") String text) {
        return this.tagMapper.mapListTagToListTagDto(tagService.findByName(text));
    }

    //    "/tag/add?name={name} - add new tag
    // добавление в Tags нового тэга
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag add(@RequestParam("name") String name) {

        Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();

        return tagService.save(new Tag(name, dateAddNote));
    }

    //  /tag/update?id={id}?name={name} - update tag by id
    @GetMapping("/update")
    public void updateById(@RequestParam("id") Integer id,
                           @RequestParam("name") String name) {
        tagService.update(id, name);
    }

    //  /tag/delete?id={id} - delete tag by id
    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        tagService.deleteById(id);
    }

}
