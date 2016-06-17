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
        Assert.assertEquals(4,stories.size());
    }

    @Test
    public void testGetStoryById(){
        Story story= storyDao.byId(1);
        Assert.assertNotNull(story);
        Assert.assertEquals("Forget-Me-Not",story.getTitle());
    }


    @Test
    public void saveUpdateDeleteStory(){

        Story story=new Story();

        story.setTitle("The story I wrote");
        story.setText("This story goes on and on and on.....");

        int id= storyDao.saveStory(story);

        Assert.assertNotNull(id);

        //now retrieve it
        Story s= storyDao.byId(id);
        Assert.assertNotNull(s);
        Assert.assertEquals("The story I wrote",s.getTitle());

        //now update it
        s.setTitle("The story I wrote again");
        storyDao.updateStory(s);

        Story _w= storyDao.byId(id);
        Assert.assertNotNull(_w);
        Assert.assertEquals("The story I wrote again",_w.getTitle());

        //now delete it
        storyDao.deleteStory(id);
        s= storyDao.byId(id);

        Assert.assertNull(s);

    }

}
