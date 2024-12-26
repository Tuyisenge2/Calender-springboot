package com.example.demoJava.config;

import com.example.demoJava.models.Content;
import com.example.demoJava.repository.ContentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;


@Profile("!dev") //this not allow this compnent to run in dev mode
@Component
public class DataLoader implements CommandLineRunner {

    private  ContentRepository repository;
    private  ObjectMapper objectMapper;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World Data Loader");
        try(InputStream inputStream= TypeReference.class.getResourceAsStream("/data/content.json")){
            repository.saveAll(objectMapper.readValue(inputStream,new com.fasterxml.jackson.core.type.TypeReference<List<Content>>(){}));
        }
    }
}
