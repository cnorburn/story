package com.aerian.bbc.dao;

import com.aerian.bbc.entities.Writer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:/resources/test-context.xml")
@Transactional
public class WriterDaoTest {

    private static EmbeddedDatabase db;
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    WriterDao writerDao;

    @Test
    public void testGetWriters(){
        List<Writer> writers=writerDao.selectAll();

        System.out.println(writers);

        Assert.assertNotNull(writers);
        Assert.assertEquals(3,writers.size());
    }

    @Test
    public void testGetWriterById(){
        Writer writer=writerDao.byId(1);
        Assert.assertNotNull(writer);
        Assert.assertEquals("Ella Higgins",writer.getName());
    }


}
