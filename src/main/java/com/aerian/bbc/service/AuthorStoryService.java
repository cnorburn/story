package com.aerian.bbc.service;

import com.aerian.bbc.dao.AuthorStoryDao;
import com.aerian.bbc.entities.AuthorStories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorStoryService {

    @Autowired
    AuthorStoryDao authorStoryDao;


    public List<AuthorStories> getAll(){
        return authorStoryDao.selectAll();
    }


}
