package com.aerian.bbc.dao;

import com.aerian.bbc.entities.Writer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class WriterDao {

    @PersistenceContext
    EntityManager entityManager;
    private EntityManagerFactory writerDao;

    public void setEntityManagerFactoryBean(EntityManagerFactory entityManager) {
        entityManager=entityManager;
    }


    public int saveStory(Writer writer){
        entityManager.persist(writer);
        return writer.getId();
    }

    public void deleteStory(int id){
        Writer w= entityManager.find(Writer.class,id);
        entityManager.remove(w);
    }

    public void updateStory(Writer writer){

        Writer w= entityManager.find(Writer.class,writer.getId());
        w.setName(writer.getName());
        w.setSchool(writer.getSchool());
        w.setDob(writer.getDob());
        w.setGender(writer.getGender());
        w.setTitle(writer.getTitle());
        w.setText(writer.getText());

    }

    public List<Writer> selectAll(){
        List<Writer> writers=entityManager.createQuery("FROM Writer").getResultList();
        return writers;
    }

    public Writer byId(int id){
        return entityManager.find(Writer.class,id);
    }

    public void setWriterDao(EntityManagerFactory writerDao) {
        this.writerDao = writerDao;
    }

    public EntityManagerFactory getWriterDao() {
        return writerDao;
    }

}
