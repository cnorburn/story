package com.aerian.bbc.service;

import com.aerian.bbc.dao.AuthorDao;
import com.aerian.bbc.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorDao authorDao;

    public List<Author> getAll(){
        return authorDao.selectAll();
    }

    public Author byId(int id){
        return authorDao.byId(id);
    }

    public int saveStory(Author author){
        return authorDao.saveAuthor(author);
    }

    public void updateStory(Author author){
        authorDao.updateAuthor(author);
    }

    public void deleteStory(int id){
        authorDao.deleteAuthor(id);
    }


}
