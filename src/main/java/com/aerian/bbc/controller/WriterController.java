package com.aerian.bbc.controller;


import com.aerian.bbc.entities.Writer;
import com.aerian.bbc.service.WriterService;
import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/writers")
public class WriterController {

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/", method= GET)
    @JsonView(Views.class)
    public List<Writer> all(){

        System.out.println(writerService.getAll());

        return writerService.getAll();
    }

    @RequestMapping(value="/{id}",method = GET)
    @JsonView(Views.class)
    public Writer id(@PathVariable("id") int id){
        return writerService.byId(id);
    }

    @RequestMapping(value="/",method = POST)
    public void saveStory(@RequestBody Writer writer){
        writerService.saveStory(writer);
    }

    @RequestMapping(value="/",method = PUT)
    public void updateStory(@RequestBody Writer writer){
        writerService.updateStory(writer);
    }

    @RequestMapping(value="/{id}",method = DELETE)
    public void deleteStory(@PathVariable("id") int id){
        writerService.deleteStory(id);
    }


}
