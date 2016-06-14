package com.aerian.bbc.controller;


import com.aerian.bbc.entities.Story;
import com.aerian.bbc.exception.StoryNotFoundException;
import com.aerian.bbc.service.StoryService;
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
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @RequestMapping(value = "/", method=GET)
    @JsonView(Views.class)
    public List<Story> all(){
        return storyService.getAll();
    }

    @RequestMapping(value="/{id}",method=GET)
    @JsonView(Views.class)
    public Story id(@PathVariable("id") int id) throws StoryNotFoundException {
        Story story= storyService.byId(id);
        if(story==null){
            throw new StoryNotFoundException(id);
        }
        return story;
    }

    @RequestMapping(value="/",method=POST)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Integer> saveStory(@RequestBody Story story){
        return Collections.singletonMap("id", storyService.saveStory(story));
    }

    @RequestMapping(value="/",method=PUT)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> updateStory(@RequestBody Story story) throws StoryNotFoundException {
        Story s= storyService.byId(story.getId());
        if(s==null){
            throw new StoryNotFoundException(s.getId());
        }
        storyService.updateStory(story);
        return Collections.singletonMap("success", true);
    }

    @RequestMapping(value="/{id}",method=PUT)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> updateStory(@PathVariable("id") int id, @RequestBody Story story) throws StoryNotFoundException {
        story.setId(id);
        Story s= storyService.byId(id);
        if(s==null){
            throw new StoryNotFoundException(id);
        }
        storyService.updateStory(story);
        return Collections.singletonMap("success", true);
    }

    @RequestMapping(value="/{id}",method=DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteStory(@PathVariable("id") int id)  throws StoryNotFoundException {
        try{
            storyService.deleteStory(id);
            return Collections.singletonMap("success", true);
        } catch (Exception ex){
            throw new StoryNotFoundException(id);
        }
    }


    @ExceptionHandler(StoryNotFoundException.class)
    @JsonView(Views.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private Map<String,String> handleStoryNotFoundException(Exception ex){

        Map<String,String> map = new LinkedHashMap<String, String>();
        map.put("success", "false");
        map.put("message", ex.getMessage().toString());

        return map;
    }


}
