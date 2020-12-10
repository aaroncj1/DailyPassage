package com.aaroncj1.DailyPassage.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.concurrent.ScheduledExecutorService;

@Data
public class Schedule {

    @JsonProperty("Day")
    public String day;

    @JsonProperty("Book")
    public String book;

    @JsonProperty("Chapter")
    public String chapter;

    @JsonProperty("Psa")
    public String psalm;

    @JsonProperty("Video")
    public String video;

    public Schedule(){
    }

    public Schedule(String book, String chapter, String psalm, String video)
    {
        this.book = book;
        this.chapter = chapter;
        this.psalm = psalm;
        this.video = video;
    }
}
