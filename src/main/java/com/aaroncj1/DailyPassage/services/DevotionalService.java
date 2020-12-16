package com.aaroncj1.DailyPassage.services;

import com.aaroncj1.DailyPassage.DAO.Schedule;
import com.aaroncj1.DailyPassage.Response.DailyPassageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;

@Component
public class DevotionalService {

    @Autowired
    private ESVAPI esvapi;

    @Autowired
    private BibleAPI bibleAPI;

    DailyPassageResponse dailyPassageResponse = new DailyPassageResponse();


    public String retrievePassage(Integer inputDay) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Schedule[] schedule = objectMapper.readValue(new File("src/main/resources/static/BPSchedule.json"), Schedule[].class);

        int today = (inputDay > schedule.length) ? schedule.length : inputDay;
        today = (today < 0) ? 1 : today;
        Schedule todayReading = schedule[today-1];

        String passage;
        String psalmText;
        String embedVideo = /*if*/ (todayReading.video != "" && todayReading.video != null) ? /*then*/ embedVideo = "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video + "></iframe><div>\n" : /*else*/ "";
        String embedVideo2 = /*if*/ (todayReading.video2 != "" && todayReading.video2 != null) ? /*then*/ embedVideo2 = "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video2 + "></iframe><div>\n" : /*else*/ "";
        try {
            //passage = esvapi.getPassage(todayReading.book, todayReading.chapter);
            passage = bibleAPI.getPassage(todayReading.book, todayReading.chapter);
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
        dailyPassageResponse.setVideo2(embedVideo2);


        return dailyPassageResponse.toString();
    }
    public String retrievePassage() throws Exception {
        int today = LocalDate.now().getDayOfYear();
        ObjectMapper objectMapper = new ObjectMapper();

        Schedule[] schedule = objectMapper.readValue(new File("src/main/resources/static/BPSchedule.json"), Schedule[].class);
        today = Math.min(today, schedule.length);
        Schedule todayReading = schedule[today-1];

        String passage;
        String psalmText;
        String embedVideo = /*if*/ (todayReading.video != "" && todayReading.video != null) ? /*then*/ embedVideo = "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video + "></iframe><div>\n" : /*else*/ "";
        String embedVideo2 = /*if*/ (todayReading.video2 != "" && todayReading.video2 != null) ? /*then*/ embedVideo2 = "<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video2 + "></iframe><div>\n" : /*else*/ "";

        try {
            passage = esvapi.getPassage(todayReading.book, todayReading.chapter);
            psalmText = esvapi.getPassage("Psalms", todayReading.psalm);
        } catch (Exception exception) {
            passage = "failed to get passage from ESV API";
            psalmText = "failed to get passage from ESV API";
        }

        System.out.println("Showing Passage");
        dailyPassageResponse.setBook(todayReading.book);
        dailyPassageResponse.setChapter(todayReading.chapter);
        dailyPassageResponse.setPassage(passage);
        dailyPassageResponse.setVideo(embedVideo);
        dailyPassageResponse.setVideo(embedVideo2);
        dailyPassageResponse.setPsalm(todayReading.psalm);
        dailyPassageResponse.setPsalmText(psalmText);
        dailyPassageResponse.setVideo(embedVideo2);

        return dailyPassageResponse.toString();
    }
}
