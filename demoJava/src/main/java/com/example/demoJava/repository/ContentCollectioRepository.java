package com.example.demoJava.repository;

import com.example.demoJava.models.Content;
import com.example.demoJava.models.Status;
import com.example.demoJava.models.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectioRepository {

    private  final List<Content> contentList = new ArrayList<>();
    public  ContentCollectioRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById (Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public  void save (Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    @PostConstruct
    private void init(){
        Content c = new Content(
                1,
                "my First Blog",
                "desc",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );

        contentList.add(c);
    }

    public  boolean existById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).count()==1;
    }

    public  void delete(Integer id){
        contentList.removeIf(c -> c.id().equals(id));
    }
}
