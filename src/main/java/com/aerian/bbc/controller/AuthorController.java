package com.aerian.bbc.controller;

import com.aerian.bbc.entities.Author;
import com.aerian.bbc.service.AuthorService;
import com.aerian.bbc.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/", method=GET)
    @JsonView(Views.class)
    public List<Author> all(){
        return authorService.getAll();
    }

}
