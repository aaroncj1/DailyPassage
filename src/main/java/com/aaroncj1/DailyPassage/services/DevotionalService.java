package com.aaroncj1.DailyPassage.services;

import com.aaroncj1.DailyPassage.DAO.Schedule;
import com.aaroncj1.DailyPassage.Response.DailyPassageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class DevotionalService {

    @Autowired
    private ESVAPI esvapi;

    @Autowired
    private BibleAPI bibleAPI;

    DailyPassageResponse dailyPassageResponse = new DailyPassageResponse();

    public String retrievePassage(Integer inputDay, String translation) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream stream = DevotionalService.class.getResourceAsStream("/static/BPSchedule.json");
        Schedule[] schedule = objectMapper.readValue(stream, Schedule[].class);
        int today = (inputDay > schedule.length) ? schedule.length : inputDay;
        today = (today <= 0) ? 1 : today;
        Schedule todayReading = schedule[today-1];

        String passage;
        String psalmText = null;
        String embedVideo = /*if*/ (todayReading.video != "" && todayReading.video != null) ? /*then*/ "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video + "></iframe>\n" : /*else*/ "";
        String embedVideo2 = /*if*/ (todayReading.video2 != "" && todayReading.video2 != null) ? /*then*/ "<iframe width=\"620\" height=\"415\" src=" + todayReading.video2 + "></iframe></div>\n" : /*else*/ "";
        try {
            passage = (translation.equalsIgnoreCase("esv") || (translation.isEmpty())) ? bibleAPI.getESVPassage(todayReading.book, todayReading.chapter) : bibleAPI.getPassage(todayReading.book, todayReading.chapter, translation);
            psalmText = esvapi.getPassage("Psalms", todayReading.psalm);
        } catch (Exception exception) {
            passage = "failed to get passage from ESV API";
        }

        dailyPassageResponse.setBook(todayReading.book);
        dailyPassageResponse.setChapter(todayReading.chapter);
        dailyPassageResponse.setPassage(passage);
        dailyPassageResponse.setVideo(embedVideo);
        dailyPassageResponse.setVideo2(embedVideo2);
        dailyPassageResponse.setPsalm(todayReading.psalm);
        dailyPassageResponse.setPsalmText(psalmText);

        return dailyPassageResponse.toString();
    }
    public String retrievePassage(String translation) throws Exception {
        int today = LocalDate.now(ZoneId.of("America/Chicago")).getDayOfYear();
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream stream = DevotionalService.class.getResourceAsStream("/static/BPSchedule.json");
        Schedule[] schedule = objectMapper.readValue(stream, Schedule[].class);
        today = Math.min(today, schedule.length);
        Schedule todayReading = schedule[today-1];

        String passage;
        String psalmText;
        String embedVideo = /*if*/ (todayReading.video != "" && todayReading.video != null) ? /*then*/ "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video + "></iframe><div>\n" : /*else*/ "";
        String embedVideo2 = /*if*/ (todayReading.video2 != "" && todayReading.video2 != null) ? /*then*/ "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video2 + "></iframe><div>\n" : /*else*/ "";

        try {
            passage = (translation.equalsIgnoreCase("esv") || (translation.isEmpty())) ? bibleAPI.getESVPassage(todayReading.book, todayReading.chapter) : bibleAPI.getPassage(todayReading.book, todayReading.chapter, translation);
            psalmText = esvapi.getPassage("Psalms", todayReading.psalm);
        } catch (Exception exception) {
            passage = "failed to get passage from ESV API";
            psalmText = "failed to get passage from ESV API";
        }

        dailyPassageResponse.setBook(todayReading.book);
        dailyPassageResponse.setChapter(todayReading.chapter);
        dailyPassageResponse.setPassage(passage);
        dailyPassageResponse.setVideo(embedVideo);
        dailyPassageResponse.setVideo2(embedVideo2);
        dailyPassageResponse.setPsalm(todayReading.psalm);
        dailyPassageResponse.setPsalmText(psalmText);

        return dailyPassageResponse.toString();
    }
}
