package com.aaroncj1.DailyPassage.Response;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;

@Data
public class DailyPassageResponse {
    private String book;
    private String chapter;
    private String passage;
    private String psalm;
    private String psalmText;
    private String video;
    private String video2;

    public String getBook() {
        return book;
    }

    public String getChapter() {
        return chapter;
    }

    public String getPassage() {
        return passage;
    }

    public String getPsalm() {
        return psalm;
    }

    public String getPsalmText() {
        return psalmText;
    }

    public String getVideo() {
        return video;
    }

    public String getVideo2() {
        return video2;
    }

    public void setBook(String book){
        this.book = book;
    }
    public void setChapter(String chapter){
        this.chapter = chapter;
    }
    public void setPassage(String passage){
        this.passage = passage;
    }
    public void setPsalm(String psalm){
        this.psalm = psalm;
    }
    public void setPsalmText(String psalmText){
        this.psalmText = psalmText;
    }
    public void setVideo(String video){
        this.video = video;
    }
    public void setVideo2(String video2){
        this.video2 = video2;
    }
}
