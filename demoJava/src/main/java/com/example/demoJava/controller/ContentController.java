package com.example.demoJava.controller;

import com.example.demoJava.models.Content;
import com.example.demoJava.models.Status;
import com.example.demoJava.models.Type;
import com.example.demoJava.repository.ContentCollectioRepository;
import com.example.demoJava.repository.ContentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentRepository repository;
  //  private final ContentCollectioRepository repository;


    public  ContentController(ContentRepository repository){
        this.repository=repository;
}
/// make request and find alll pieces of content

    @GetMapping("")
    public List<Content> findAll(){
     return  repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById (@PathVariable Integer id){
        return  repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
       repository.save(content);

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content,@PathVariable Integer id){
        if(!repository.existsById(id)){ //existsById
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,"id not found");
        }
     repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public  void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle( @PathVariable String keyword){
        return repository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus( @PathVariable Status status){
        return repository.listByStatus(status);
    }
    }
