package com.example.links.web;

import com.example.links.service.BLRecordService;
import com.example.links.utils.mapper.BLRecordMapper;
import com.example.links.dto.BLRecordDto;
import com.example.links.entity.BLRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/bl_record")
@RequiredArgsConstructor
public class BLRecordRestController {

    private final BLRecordService blRecordService;

    private final BLRecordMapper blRecordMapper;

    //bl_record/all - find all records
    @GetMapping("/all")
    public List<BLRecordDto> findAll() {
        return this.blRecordMapper.mapListBLRecordToListBLRecordDto(blRecordService.findALL());
    }

    //  /bl_record/find_by_domain?text={text} - find by domain
    @GetMapping("/find_by_domain")
    public List<BLRecordDto> findByName(@RequestParam("text") String text) {
        return this.blRecordMapper.mapListBLRecordToListBLRecordDto(blRecordService.findByName(text));
    }

    //    "/bl_record/add?domain={domain} - add new BLRecord
    // добавление в BLRecords новой категории
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public BLRecord add(@RequestParam("domain") String domain) {

        Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();

        return blRecordService.save(new BLRecord(domain, dateAddNote));
    }

    //  /bl_record/update?id={id}?domain={domain} - update BLRecord by id
    @GetMapping("/update")
    public void updateById(@RequestParam("id") Integer id,
                           @RequestParam("domain") String domain) {
        blRecordService.update(id, domain);
    }

    //  /bl_record/delete?bl_record_id={bl_record_id} - delete BLRecord by id
    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        blRecordService.deleteById(id);
    }
}
