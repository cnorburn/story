package com.aerian.bbc.controller;


import com.aerian.bbc.dao.WriterDao;
import com.aerian.bbc.entities.Writer;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping("/writer")
public class WriterController {

    @Autowired
    private WriterDao writerDao;

    @RequestMapping(value = "/writers", method= GET)
    @JsonView(WriterDao.class)
    public List<Writer> all(){
        return writerDao.selectAll();
    }

}
