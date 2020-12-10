package com.aaroncj1.DailyPassage.services;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class ESVAPI {

    public String getPassage(String book, String chapter) throws Exception {
        StringBuilder urlStringBuilder = new StringBuilder();
        urlStringBuilder.append("http://www.esvapi.org/v2/rest/passageQuery");
        urlStringBuilder.append("?key=TEST");
        urlStringBuilder.append("&passage=").append(URLEncoder.encode(book + " " + chapter, StandardCharsets.ISO_8859_1));

        urlStringBuilder.append("&include-headings=true");
        System.out.println(urlStringBuilder.toString());

        URL esvURL = new URL(urlStringBuilder.toString());
        InputStream esvStream = esvURL.openStream();

        StringBuilder outStringBuilder = new StringBuilder();
        int nextChar;

        while ((nextChar = esvStream.read()) != -1) {
            outStringBuilder.append((char) nextChar);
        }

        esvStream.close();
        System.out.println(outStringBuilder.toString());

        return outStringBuilder.toString();
    }
}