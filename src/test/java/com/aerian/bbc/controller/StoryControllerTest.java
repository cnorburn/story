//package com.aerian.bbc.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//public class StoryControllerTest {
//
//    MockMvc mockMvc;
//
//    @Before
//    public void init(){
//        mockMvc = MockMvcBuilders.standaloneSetup(StoryController.class).build();
//    }
//
//    @Test
//    public void getAllStories() throws Exception {
//        mockMvc.perform(get("/stories/")).andExpect(status().isOk());
//    }
//
//}
