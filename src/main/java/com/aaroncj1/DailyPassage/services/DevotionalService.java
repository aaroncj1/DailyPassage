package com.aaroncj1.DailyPassage.services;

import com.aaroncj1.DailyPassage.DAO.Schedule;
import com.aaroncj1.DailyPassage.Response.DailyPassageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DevotionalService {
    @Autowired
    private ESVAPI esvapi;

    public String retrievePassage() throws Exception {
        DailyPassageResponse dailyPassageResponse = new DailyPassageResponse();
        //int today = LocalDate.now().getDayOfYear();
        int today = 1;
        ObjectMapper objectMapper = new ObjectMapper();

        Schedule[] schedule = objectMapper.readValue(new File("src/main/resources/static/BPSchedule.json"), Schedule[].class);
        Schedule todayReading = schedule[today];

        String passage;
        String psalmText;

        System.out.println(todayReading.book + todayReading.chapter + todayReading.psalm + todayReading.video);

        try {
             passage = esvapi.getPassage(todayReading.book, todayReading.chapter);
             psalmText = esvapi.getPassage("Psalms", todayReading.psalm);
        }
        catch (Exception exception)
        {
            passage = "failed";
            psalmText = "failed";
        }

        System.out.println(passage + psalmText);


        dailyPassageResponse.setBook(todayReading.book);
        dailyPassageResponse.setChapter(todayReading.chapter);
        dailyPassageResponse.setPassage(passage);
        dailyPassageResponse.setVideo(todayReading.video);
        dailyPassageResponse.setPsalm(todayReading.psalm);
        dailyPassageResponse.setPsalmText(psalmText);

        return dailyPassageResponse.toString();
    }
}
