package com.aaroncj1.DailyPassage.Response;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;

@Data
public class DailyPassageResponse {

    private String style = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "<style>\n" +
                            "h2   {color: blue;}\n" +
                            "p    {color: black;}\n" +
                            "body    {padding-left: 100px; padding-right:100px;}\n" +
                            "</style>\n" +
                            "<title>Today's Reading</title>" +
                            "</head>\n" +
                            "<body>\n";
    private String book;
    private String chapter;
    private String passage;
    private String psalm;
    private String psalmText;
    private String video;
    private String video2;

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


    @Override
    public String toString(){
        String endTag = "</body></html>";
        int today = LocalDate.now(ZoneId.of("America/Chicago")).getDayOfYear();
        String now = LocalDate.now(ZoneId.of("America/Chicago")).toString();
        return style + "<h1> Day: " + today + "; Date: " + now + "</h1>" + passage + " " +  video + " " + video2+ " " + psalmText + endTag;
    }

}
