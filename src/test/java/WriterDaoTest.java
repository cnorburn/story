package java;

import com.aerian.bbc.dao.WriterDao;
import com.aerian.bbc.entities.Writer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SqlGroup({
//        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "data.sql"),
//        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "destroy.sql") })
public class WriterDaoTest {


    private EmbeddedDatabase db;

    @Autowired
    WriterDao writerDao;


    @Before
    public void setUp(){

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        db = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:/resources/sql/data.sql")
                .build();

    }



    @Test
    public void testGetWriters(){

        List<Writer> writers=writerDao.selectAll();

        Assert.assertNotNull(writers);


    }


}
