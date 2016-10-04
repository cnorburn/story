package com.aerian.bbc.dao;

import com.aerian.bbc.entities.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public int saveAuthor(Author author){
        entityManager.persist(author);
        return author.getId();
    }

    public void deleteAuthor(int id){
        Author a= entityManager.find(Author.class,id);
        entityManager.remove(a);
    }

    public void updateAuthor(Author author){
        Author a= entityManager.find(Author.class,author.getId());
        a.setName(author.getName());
    }

    public List<Author> selectAll(){
        List<Author> storys=entityManager.createQuery("FROM Author").getResultList();
        return storys;
    }

    public Author byId(int id){
        return entityManager.find(Author.class,id);
    }


}
