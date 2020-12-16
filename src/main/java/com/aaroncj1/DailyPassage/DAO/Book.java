package com.aaroncj1.DailyPassage.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Book {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("bibleId")
    public String bibleId;

    @JsonProperty("abbreviation")
    public String abbreviation;

    @JsonProperty("nameLong")
    public String nameLong;

    public Book(){
    }

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
