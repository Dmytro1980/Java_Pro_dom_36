package com.example.links.service;

import com.example.links.entity.BLRecord;
import com.example.links.repository.BLRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BLRecordService {

    private final BLRecordRepository blRecordRepository;


    public List<BLRecord> findALL() {
        return this.blRecordRepository.findAll();
    }

    public List<BLRecord> findByName(String text) {
        return this.blRecordRepository.findByDomainContaining(text);
    }

    public BLRecord save(BLRecord record){
        return this.blRecordRepository.save(record);
    }

    public void update(Integer id, String domain) {

        if (this.blRecordRepository.existsById(id)) {
            this.blRecordRepository.updateById(id, domain);
        }
    }

    public void deleteById(Integer id) {
        this.blRecordRepository.deleteById(id);
    }

    public boolean existsByDomain(String domain) {
        return this.blRecordRepository.existsByDomainEndsWithIgnoreCase(domain);
    }
}
