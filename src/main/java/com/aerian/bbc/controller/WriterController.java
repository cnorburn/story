package com.aerian.bbc.controller;


import com.aerian.bbc.entities.Writer;
import com.aerian.bbc.exception.WriterNotFoundException;
import com.aerian.bbc.service.WriterService;
import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/writers")
public class WriterController{

    @Autowired
    private WriterService writerService;

    @RequestMapping(value = "/", method=GET)
    @JsonView(Views.class)
    public List<Writer> all(){
        return writerService.getAll();
    }

    @RequestMapping(value="/{id}",method=GET)
    @JsonView(Views.class)
    public Writer id(@PathVariable("id") int id) throws WriterNotFoundException {
        Writer writer= writerService.byId(id);

        if(writer==null){
            throw new WriterNotFoundException(id);
        }
        return writer;

    }

    @RequestMapping(value="/",method=POST)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Integer> saveStory(@RequestBody Writer writer){
        return Collections.singletonMap("id", writerService.saveStory(writer));
    }

    @RequestMapping(value="/",method=PUT)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> updateStory(@RequestBody Writer writer) throws WriterNotFoundException {
        Writer w= writerService.byId(writer.getId());
        if(w==null){
            throw new WriterNotFoundException(w.getId());
        }
        writerService.updateStory(writer);
        return Collections.singletonMap("success", true);
    }

    @RequestMapping(value="/{id}",method=PUT)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> updateStory(@PathVariable("id") int id, @RequestBody Writer writer) throws WriterNotFoundException {
        writer.setId(id);
        Writer w= writerService.byId(id);
        if(w==null){
            throw new WriterNotFoundException(id);
        }
        writerService.updateStory(writer);
        return Collections.singletonMap("success", true);
    }

    @RequestMapping(value="/{id}",method=DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteStory(@PathVariable("id") int id){
        writerService.deleteStory(id);
        return Collections.singletonMap("success", true);
    }


    @ExceptionHandler(WriterNotFoundException.class)
    @JsonView(Views.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private Map<String,String> handleWriterNotFoundException(Exception ex){

        Map<String,String> map = new LinkedHashMap<String, String>();
        map.put("success", "false");
        map.put("message", ex.getMessage().toString());

        return map;
    }


}
