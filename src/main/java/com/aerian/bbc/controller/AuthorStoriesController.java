package com.aerian.bbc.controller;

import com.aerian.bbc.entities.AuthorStories;
import com.aerian.bbc.service.AuthorStoryService;
import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/author/stories")
public class AuthorStoriesController {

    @Autowired
    AuthorStoryService authorStoryService;

    @RequestMapping(value = "/", method=GET)
    @JsonView(Views.class)
    public List<AuthorStories> all(){
        return authorStoryService.getAll();
    }


}
