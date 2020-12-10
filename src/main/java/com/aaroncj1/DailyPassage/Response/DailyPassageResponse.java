package com.aaroncj1.DailyPassage.Response;

import lombok.Data;

@Data
public class DailyPassageResponse {

    private String book;
    private String chapter;
    private String passage;
    private String psalm;
    private String psalmText;
    private String video;

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

    @Override
    public String toString(){
        return book + " " + chapter + " " + passage + " " +  video + " " + psalm + " " + psalmText + " ";
    }

}
