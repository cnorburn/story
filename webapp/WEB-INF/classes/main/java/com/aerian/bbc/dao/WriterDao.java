package com.aerian.bbc.dao;

import com.aerian.bbc.entities.Writer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional(readOnly = false)
public class WriterDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveStory(Writer writer){
        entityManager.persist(writer);

        System.out.println("--Data Saved--");
    }


    public List<Writer> selectAll(){
        List<Writer> writers=entityManager.createQuery("FROM Writer").getResultList();
        return writers;
    }

}
