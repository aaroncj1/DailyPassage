package com.aaroncj1.DailyPassage.services;

import com.aaroncj1.DailyPassage.Response.ESVResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class ESVAPI {

    public String getPassage(String book, String chapter) throws Exception {
        String url = "https://api.esv.org/v3/passage/text/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("q", book + chapter);


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Token 926e88a4835caaf8e0d2fdb7483c4a01e828f9d2");
        headers.set("Content-Type", "text/html; charset=utf-8");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                String.class
        );

// check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

        //change unicode chars


        return response.getBody();
    }
}