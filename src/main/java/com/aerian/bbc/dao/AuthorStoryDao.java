package com.aerian.bbc.dao;

import com.aerian.bbc.entities.AuthorStories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AuthorStoryDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<AuthorStories> selectAll(){
        List<AuthorStories> storys=entityManager.createQuery("FROM AuthorStories").getResultList();
        return storys;
    }


}
