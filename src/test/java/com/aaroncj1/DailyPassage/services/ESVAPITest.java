package com.aaroncj1.DailyPassage.services;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ESVAPITest {

    @Test
    void getPassageTest() throws Exception {
        ESVAPI esvapi = new ESVAPI();
        String expected = "In the beginning";
        String actual = esvapi.getPassage("John", "1:1");
        assertThat(actual, CoreMatchers.containsString(expected));
    }
}
