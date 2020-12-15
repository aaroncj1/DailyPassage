package com.aaroncj1.DailyPassage.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class BibleAPI {

    @Value("${apiKey}")
    String apiKey;

    @Value("${version.kjv}")
    String kjv;

    public String getPassage(String book, String chapter) throws Exception {

        StringBuilder urlStringBuilder = new StringBuilder();

        urlStringBuilder.append("https://api.scripture.api.bible/v1/bibles/" + kjv + "/passages/" + book + "." + chapter +"?content-type=html&include-notes=false&include-titles=true&include-chapter-numbers=false&include-verse-numbers=true&include-verse-spans=false&use-org-id=false");

        URL bibleURL = new URL(urlStringBuilder.toString());
        InputStream esvStream = bibleURL.openStream();

        StringBuilder outStringBuilder = new StringBuilder();
        int nextChar;

        while ((nextChar = esvStream.read()) != -1) {
            outStringBuilder.append((char) nextChar);
        }

        esvStream.close();

        return outStringBuilder.toString();
    }
}