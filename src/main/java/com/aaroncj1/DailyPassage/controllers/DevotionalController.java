package com.aaroncj1.DailyPassage.controllers;

import com.aaroncj1.DailyPassage.services.DevotionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DevotionalController {
    @Autowired
    DevotionalService devotionalService;

    @GetMapping(value = {"{translation}/retrievePassage/{day}"})
    @ResponseBody
    public ResponseEntity<String> retrievePassage(@PathVariable("day")Integer day, @PathVariable("translation")String translation) {
        try {
            return new ResponseEntity<>(devotionalService.retrievePassage(day, translation), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"{translation}/retrievePassage"})
    @ResponseBody
    public ResponseEntity<String> retrievePassage(@PathVariable("translation")String translation) {
        try {
            return new ResponseEntity<>(devotionalService.retrievePassage(translation), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/retrievePassage/{day}"})
    @ResponseBody
    public ResponseEntity<String> retrievePassage(@PathVariable("day")Integer day) {
        try {
            return new ResponseEntity<>(devotionalService.retrievePassage(day, ""), HttpStatus.OK);
        }
        catch (Exception ex) {
            System.out.println(ex + "/day");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"/retrievePassage"})
    @ResponseBody
    public ResponseEntity<String> retrievePassage() {
        try {
            return new ResponseEntity<>(devotionalService.retrievePassage(""), HttpStatus.OK);
        }
        catch (Exception ex) {
            System.out.println(ex + "/");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = {"/"})
    @ResponseBody
    public ResponseEntity<String> retrievePassage_v2() {
        try {
            return new ResponseEntity<>(devotionalService.retrievePassage(""), HttpStatus.OK);
        }
        catch (Exception ex) {
            System.out.println(ex + "/");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = {"/retrievePassage/{day}"})
    @ResponseBody
    public ResponseEntity<String> retrievePassage_v2(@PathVariable("day")Integer day) {
        try {
            return new ResponseEntity<>(devotionalService.retrievePassage(day, ""), HttpStatus.OK);
        }
        catch (Exception ex) {
            System.out.println(ex + "/day");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
