package com.example.demoJava.controller;

import com.example.demoJava.config.ContentCalendarProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
//    @Value("${cc.message:Default Welcome message}")
//    private String message;
//
//    @Value("{cc.about}")
//private String about;

    private final ContentCalendarProperties properties;

    public HomeController(ContentCalendarProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    public ContentCalendarProperties home() {
        //return Map.of("welcomeMessage",message,"about",about);
        return properties;
    }
}
