package com.aaroncj1.DailyPassage.util;

public class EsvResponseUtil {

    public String parsePassage(String passage){
        String str = passage.replace("\\n", "");

        str = str.replace("\\u2013", "-");
        str = str.replace("\\u2014", "--");

       // str = str.replace("\\u201c", "\"");
       // str = str.replace("\\u201d", "\"");

        str = str.replace("\\u2018", "'");
        str = str.replace("\\u2019", "'");

        return str;
    }
}
