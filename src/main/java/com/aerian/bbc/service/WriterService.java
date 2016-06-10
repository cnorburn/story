package com.aerian.bbc.service;

import com.aerian.bbc.dao.WriterDao;
import com.aerian.bbc.entities.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterService {

    @Autowired
    WriterDao writerDao;

    public List<Writer> getAll(){
        return writerDao.selectAll();
    }

    public Writer byId(int id){
        return writerDao.byId(id);
    }

    public int saveStory(Writer writer){
        return writerDao.saveStory(writer);
    }

    public void updateStory(Writer writer){
        writerDao.updateStory(writer);
    }

    public void deleteStory(int id){
        writerDao.deleteStory(id);
    }


}
