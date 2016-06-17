package com.aerian.bbc.controller;

import com.aerian.bbc.configuration.JPATestConfig;
import com.aerian.bbc.entities.Story;
import com.aerian.bbc.service.StoryService;
import com.jayway.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,classes = {JPATestConfig.class})
public class StoryControllerTest {

    @Mock
    StoryService storyService;

    @InjectMocks
    StoryController controller;

    @Mock
    View mockView;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllStories() throws Exception {
        mockMvc.perform(get("/stories/")).andExpect(status().isOk());
    }

    @Test
    public void getStoryById() throws Exception {
        Story story=new Story();
        story.setTitle("story title");

        when(storyService.byId(1)).thenReturn(story);

        String content = mockMvc.perform(get("/stories/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andReturn()
                .getResponse().getContentAsString();
        JsonPath jsonPath = new JsonPath(content);
        assertThat(jsonPath.getString("title"), equalTo("story title"));

        mockMvc.perform(get("/stories/{id}",2)).andExpect(status().isNotFound());

    }

    @Test
    public void saveStory() throws Exception {

        MvcResult result = mockMvc.perform(post("/stories/").contentType(APPLICATION_JSON_UTF8)
                .content( "{ \"title\": \"" + "story title" + "\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        JsonPath jsonPath = new JsonPath(content);

        assertThat(jsonPath.getInt("id"), equalTo(0));

    }


    @Test
    public void deleteStory() throws Exception{

        String content = mockMvc.perform(delete("/stories/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andReturn()
                .getResponse().getContentAsString();

        JsonPath jsonPath = new JsonPath(content);

        assertThat(jsonPath.getString("success"), equalTo("true"));

    }

}
