package com.aaroncj1.DailyPassage.services;

import com.aaroncj1.DailyPassage.DAO.Book;
import com.aaroncj1.DailyPassage.DAO.Schedule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
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

        String urlStringBuilder = null;
        String reference = formatReference(book, chapter);
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", "bdfd1ab642fb5f64e4c98406d2ac6b90");

        urlStringBuilder = ("https://api.scripture.api.bible/v1/bibles/") + (kjv) + ("/passages/") + (reference) + ("?content-type=html&include-notes=false&include-titles=true&include-chapter-numbers=false&include-verse-numbers=true&include-verse-spans=false&use-org-id=false");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> outStringBuilder = restTemplate.exchange(urlStringBuilder, HttpMethod.GET, requestEntity, String.class);
//
//        URL bibleURL = new URL(urlStringBuilder.toString());
//        InputStream esvStream = bibleURL.openStream();
//
//        StringBuilder outStringBuilder = new StringBuilder();
//        int nextChar;
//
//        while ((nextChar = esvStream.read()) != -1) {
//            outStringBuilder.append((char) nextChar);
//        }
//
//        esvStream.close();

        return outStringBuilder.toString();
    }

    //This Bible Api takes format like GEN.1-GEN.3
    private String formatReference(String book, String chapter) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book[] bookID = objectMapper.readValue(new File("src/main/resources/static/bookId.json"), Book[].class);
        String reference = null;

        for (Book value : bookID) {
            reference = (book.equals(value.name)) ? value.id : reference;
        }
        String[] chap = chapter.split("-");

        reference = (chap.length > 1) ? reference + chap[0] + "-" + reference + chap[1] : reference + chap[0] ;

        return reference;
    }
}