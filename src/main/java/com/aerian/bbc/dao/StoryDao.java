package com.aerian.bbc.dao;

import com.aerian.bbc.entities.Story;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;


@Repository
@Transactional
public class StoryDao {

    @PersistenceContext
    EntityManager entityManager;

    public int saveStory(Story story){
        entityManager.persist(story);
        return story.getId();
    }

    public void deleteStory(int id){
        Story w= entityManager.find(Story.class,id);
        entityManager.remove(w);
    }

    public void updateStory(Story story){

        Story s= entityManager.find(Story.class,story.getId());
        s.setTitle(story.getTitle());
        s.setText(story.getText());

    }

    public List<Story> selectAll(){
        List<Story> storys=entityManager.createQuery("FROM Story").getResultList();
        return storys;
    }

    public Story byId(int id){
        return entityManager.find(Story.class,id);
    }


}
