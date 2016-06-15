package com.aerian.bbc.dao;

import com.aerian.bbc.configuration.JPATestConfig;
import com.aerian.bbc.entities.Story;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,classes = {JPATestConfig.class})
@Transactional
public class StoryDaoTest {

    @Autowired
    StoryDao storyDao=new StoryDao();

    @Test
    public void testGetStories(){
        List<Story> stories= storyDao.selectAll();

        Assert.assertNotNull(stories);
        Assert.assertEquals(3,stories.size());
    }

    @Test
    public void testGetStoryById(){
        Story story= storyDao.byId(1);
        Assert.assertNotNull(story);
        Assert.assertEquals("Ella Higgins",story.getName());
    }


    @Test
    public void saveUpdateDeleteStory(){

        Story story=new Story();

        story.setName("Jon Smythe");
        story.setDob(Date.valueOf("2001-01-01"));
        story.setSchool("Grims Academy");
        story.setGender("male");
        story.setTitle("The story I wrote");
        story.setText("This story goes on and on and on.....");

        int id= storyDao.saveStory(story);

        Assert.assertNotNull(id);

        //now retrieve it
        Story s= storyDao.byId(id);
        Assert.assertNotNull(s);
        Assert.assertEquals("Jon Smythe",s.getName());

        //now update it
        s.setGender("female");
        storyDao.updateStory(s);

        Story _w= storyDao.byId(id);
        Assert.assertNotNull(_w);
        Assert.assertEquals("female",_w.getGender());

        //now delete it
        storyDao.deleteStory(id);
        s= storyDao.byId(id);

        Assert.assertNull(s);

    }

}
