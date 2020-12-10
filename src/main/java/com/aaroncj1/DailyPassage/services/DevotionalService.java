package com.aaroncj1.DailyPassage.services;

import com.aaroncj1.DailyPassage.DAO.Schedule;
import com.aaroncj1.DailyPassage.Response.DailyPassageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;

@Component
public class DevotionalService {

    @Autowired
    private ESVAPI esvapi;

    DailyPassageResponse dailyPassageResponse = new DailyPassageResponse();


    public String retrievePassage(Integer day) throws Exception {
        //int today = LocalDate.now().getDayOfYear();

        int today = day;
        ObjectMapper objectMapper = new ObjectMapper();

        Schedule[] schedule = objectMapper.readValue(new File("src/main/resources/static/BPSchedule.json"), Schedule[].class);
        Schedule todayReading = schedule[today];

        String passage;
        String psalmText;
      //  if(dailyPassageResponse == null) {
            try {
                passage = esvapi.getPassage(todayReading.book, todayReading.chapter);
                psalmText = esvapi.getPassage("Psalms", todayReading.psalm);
            } catch (Exception exception) {
                passage = "failed";
                psalmText = "failed";
            }
        //}

        dailyPassageResponse.setBook(todayReading.book);
        dailyPassageResponse.setChapter(todayReading.chapter);
        dailyPassageResponse.setPassage(passage);
        dailyPassageResponse.setVideo("<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video + "></iframe><div>\n");
        dailyPassageResponse.setPsalm(todayReading.psalm);
        dailyPassageResponse.setPsalmText(psalmText);

        return dailyPassageResponse.toString();
    }
    public String retrievePassage() throws Exception {
        int today = LocalDate.now().getDayOfYear();
        ObjectMapper objectMapper = new ObjectMapper();

        Schedule[] schedule = objectMapper.readValue(new File("src/main/resources/static/BPSchedule.json"), Schedule[].class);
        Schedule todayReading = schedule[today];

        String passage;
        String psalmText;
        //  if(dailyPassageResponse == null) {
        try {
            passage = esvapi.getPassage(todayReading.book, todayReading.chapter);
            psalmText = esvapi.getPassage("Psalms", todayReading.psalm);
        } catch (Exception exception) {
            passage = "failed";
            psalmText = "failed";
        }
        //}

        dailyPassageResponse.setBook(todayReading.book);
        dailyPassageResponse.setChapter(todayReading.chapter);
        dailyPassageResponse.setPassage(passage);
        dailyPassageResponse.setVideo("<div><iframe width=\"620\" height=\"415\" src=" + todayReading.video + "></iframe><div>\n");
        dailyPassageResponse.setPsalm(todayReading.psalm);
        dailyPassageResponse.setPsalmText(psalmText);

        return dailyPassageResponse.toString();
    }
}
