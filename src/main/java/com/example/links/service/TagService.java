package com.example.links.service;

import com.example.links.repository.TagRepository;
import com.example.links.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;


    public List<Tag> findALL() {
        return this.tagRepository.findAll();
    }

    public List<Tag> findByName(String text) {
        return this.tagRepository.findByNameContaining(text);
    }

    public Tag save(Tag tag){
        return this.tagRepository.save(tag);
    }

    public void update(Integer id, String name) {

        if (this.tagRepository.existsById(id)) {
            this.tagRepository.updateById(id, name);
        }
    }

    public void deleteById(Integer id) {
        this.tagRepository.deleteById(id);
    }

    public Optional<Tag> findByID(Integer id){
        return this.tagRepository.findById(id);
    }

}
