package com.example.links.utils.mapper;

import com.example.links.utils.TimeConverter;
import com.example.links.dto.BLRecordDto;
import com.example.links.entity.BLRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BLRecordMapper {
    TimeConverter timeConverter = new TimeConverter();

    public BLRecordDto mapBLRecordToBLRecordDto(BLRecord record) {

        return BLRecordDto.builder()
                .domain(record.getDomain())
                .createdAt(timeConverter.longToString(record.getCreatedAt()))
                .build();
    }

    public List<BLRecordDto> mapListBLRecordToListBLRecordDto(List<BLRecord> blRecordList) {

        List<BLRecordDto> blRecordDtoList = new ArrayList<>();
        for (BLRecord r : blRecordList) {
            blRecordDtoList.add(mapBLRecordToBLRecordDto(r));
        }
        return blRecordDtoList;
    }

}
