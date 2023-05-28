package com.example.links.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TimeConverter {

    public String longToString (Long longDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zoneId = ZoneId.of("UTC+3");
        return  Instant.ofEpochMilli(longDate).atZone(zoneId).format(dateTimeFormatter);
    }

    public Long stringToLong (String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(dateString);
        return date.getTime();
    }
}
