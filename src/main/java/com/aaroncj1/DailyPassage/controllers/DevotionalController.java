package com.aaroncj1.DailyPassage.controllers;

import com.aaroncj1.DailyPassage.services.DevotionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DevotionalController {
    @Autowired
    DevotionalService devotionalService;
    HttpHeaders headers = new HttpHeaders();

    @GetMapping(value = {"/"})
    public String retrievePassage_v2(Model model) throws Exception {
        model.addAttribute("response", devotionalService.retrievePassage(null));
        return "test";

    }
    @GetMapping(value = {"/{day}"})
    public String retrievePassage_v2(@PathVariable("day")Integer day, Model model) throws Exception {
        model.addAttribute("response", devotionalService.retrievePassage(day));
        return "test";
    }
    @GetMapping(value = {"/test"})
    public String test(Model model) throws Exception {
        model.addAttribute("response", devotionalService.retrievePassage(null));
        return "test";
    }
}
