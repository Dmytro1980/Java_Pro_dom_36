package com.example.links.web;

import com.example.links.dto.LinkDto;
import com.example.links.entity.BLRecord;
import com.example.links.entity.Category;
import com.example.links.entity.Link;
import com.example.links.entity.Tag;
import com.example.links.service.BLRecordService;
import com.example.links.service.CategoryService;
import com.example.links.service.LinkService;
import com.example.links.service.TagService;
import com.example.links.utils.mapper.LinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkRestController {

    private final LinkService linkService;

    private final LinkMapper linkMapper;

    private final TagService tagService;

    private final CategoryService categoryService;

    private final BLRecordService blRecordService;

    // /link/all - find all links
    @GetMapping("/all")
    public List<LinkDto> findAll() {
        List<LinkDto> linkDtoList = this.linkMapper.mapListLinkToListLinkDto(linkService.findAll());
        if (!linkDtoList.isEmpty()) {
            return linkDtoList;
        } else throw new IllegalArgumentException("no links");
    }

    // /link/all_p?page={page}?pageSize={pageSize} - получить все ссылки с пагинацией
    @GetMapping("/all_p")
    public List<LinkDto> findAllP(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize) {

        // работает
        List<LinkDto> linkDtoList = linkService.findAllP(
                        page != null ? page : 0,
                        pageSize != null ? pageSize : 5)
                .stream()
                .map(this.linkMapper::mapLinkToLinkDto)
                .toList();

        if (!linkDtoList.isEmpty()) {
            return linkDtoList;
        } else throw new IllegalArgumentException("no links");
    }


    //  /link/find_by_name_or?text={text} - find by name or value
    @GetMapping("/find_by_name_or")
    public List<LinkDto> findByNameOr(@RequestParam("text") String text) {

        List<LinkDto> linkDtoListNameAndValue = this.linkMapper.mapListLinkToListLinkDto(linkService.findByNameOr(text));

        if (!linkDtoListNameAndValue.isEmpty()) {
            return linkDtoListNameAndValue;
        } else throw new IllegalArgumentException("no name, no value");

    }

    //  /link/find_by_name_and?text={text} - find by name and value
    @GetMapping("/find_by_name_and")
    public List<LinkDto> findByNameAnd(@RequestParam("text") String text) {
        List<LinkDto> linkDtoList = this.linkMapper.mapListLinkToListLinkDto(linkService.findByNameAnd(text));

        if (!linkDtoList.isEmpty()) {
            return linkDtoList;
        } else throw new IllegalArgumentException("no names and no values");
    }

    //  /link/find_by_category?text={text} - find by category
    @GetMapping("/find_by_category")
    public List<LinkDto> findByCategory(@RequestParam("text") String text) {
        List<LinkDto> linkDtoList = new ArrayList<>();

        List<Category> list = categoryService.findByName(text);

        if (!list.isEmpty()) {
            for (Category c : list) {
                Integer id = c.getId();
                if (id != null) {
                    LinkDto linkDto = this.linkMapper.mapLinkToLinkDto(
                            linkService.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("no  category")));
                    linkDtoList.add(linkDto);
                }
            }
        } else throw new IllegalArgumentException("no category2");

        return linkDtoList;
    }


    //  /link/find_by_tag?text={text} - find by tag
    @GetMapping("/find_by_tag")
    public List<LinkDto> findByTag(@RequestParam("text") String text) {
        List<LinkDto> linkDtoList = new ArrayList<>();

        List<Tag> list = tagService.findByName(text);

        if (!list.isEmpty()) {
            for (Tag t : list) {
                Integer id = t.getId();
                if (id != null) {
                    LinkDto linkDto = this.linkMapper.mapLinkToLinkDto(linkService.findById(id).orElseThrow(() -> new IllegalArgumentException("no tag")));
                    linkDtoList.add(linkDto);
                }
            }
        } else throw new IllegalArgumentException("no tag2");

        return linkDtoList;
    }

    // /link/update?id={id}?name={name}... - update link by id
    // /link/update?id={id}&name={name}&value={value}&category={category} - update link by id
    @GetMapping("/update")
    public void updateById(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("value") String value,
                           @RequestParam("category") String category) {

        List<Category> categories = categoryService.findALL();
        Integer categoryId;

        boolean found = false;

        for (Category c : categories) {
            if (Objects.equals(c.getName(), category)) {
                found = true;
                categoryId = c.getId();
                linkService.update(id, name, value, categoryId);
            }
        }
        if (!found) {
            throw new IllegalArgumentException("category " + category + " is not created yet");
        }
    }

    //  /link/delete?id={id} - delete link by id
    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        linkService.deleteById(id);
    }

    // /link/add?name={name}... - add new link
    // /link/add?name={name}&value={value}&category={category(int)}&tag={tag}
    // добавление в Links новой ссылки
    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Link add(@RequestParam("name") String name,
                    @RequestParam("value") String value,
                    @RequestParam("category") Integer category,
                    @RequestParam(value = "tags") String... arrStringsFromParam) {

        // check value as correct URL
        try {
            URL url = new URL(value);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("value " + value + " is wrong URL");
        }


        // check value in BlackList
        boolean isInBlackList = blRecordService.existsByDomain(value);
        if(isInBlackList){
            throw new IllegalArgumentException("value " + value + " in Black List");
        }

        //===========================================

        // check category
        List<Category> categories = categoryService.findALL();
        boolean isCategoryFound = false;

        for (Category c : categories) {
            if (Objects.equals(c.getId(), category)) {
                isCategoryFound = true;
                break;
            }
        }
        if (!isCategoryFound) {
            throw new IllegalArgumentException("category " + category + " is not created yet");
        }

        //check tag

        // все теги из БД
        List<Tag> tagsFromDB = tagService.findALL();

        // сет тегов из запроса, которых нет в БД
        Set<String> stringParamNotInDB = new HashSet<>();

        // перебор всех параметров
        // мапа хранящая параметр+тег которые есть и в запросе и в БД
        Map<String, Tag> paramsAndTagsInDB = new HashMap<>();

        for (String s : arrStringsFromParam) {
            for (Tag t : tagsFromDB) {
                if (Objects.equals(t.getName(), s)) {
                    paramsAndTagsInDB.put(s, t);
                } else
                    stringParamNotInDB.add(s);

            }
        }

        if (paramsAndTagsInDB.size() == arrStringsFromParam.length) {
            Link link = new Link();
            link.setName(name);
            link.setValue(value);
            link.setCategory(category);
            Long dateNow = ZonedDateTime.now().toInstant().toEpochMilli();
            link.setCreatedAt(dateNow);

            for (Tag t : paramsAndTagsInDB.values()) {
                link.addTag(t);
            }

            return linkService.save(link);
        } else {

            List<String> tagNames = new ArrayList<>();
            for (Tag t : tagsFromDB) {
                tagNames.add(t.getName());
            }

            stringParamNotInDB.removeIf(tagNames::contains);

            throw new IllegalArgumentException("tags " + stringParamNotInDB + " is not created yet");
        }
    }
}
