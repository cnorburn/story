package com.aerian.bbc.service;

import com.aerian.bbc.dao.StoryDao;
import com.aerian.bbc.entities.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    @Autowired
    StoryDao storyDao;

    public List<Story> getAll(){
        return storyDao.selectAll();
    }

    public Story byId(int id){
        return storyDao.byId(id);
    }

    public int saveStory(Story writer){
        return storyDao.saveStory(writer);
    }

    public void updateStory(Story writer){
        storyDao.updateStory(writer);
    }

    public void deleteStory(int id){
        storyDao.deleteStory(id);
    }


}
