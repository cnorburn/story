package com.aerian.bbc.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@Configuration
@ComponentScan("com.aerian.bbc")
@EnableTransactionManagement
public class JPAConfigTwo {
    @Bean
    public JpaTransactionManager jpaTransMan(){
        JpaTransactionManager jtManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
        return jtManager;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(getDataSource());
        lcemfb.setPersistenceUnitName("localContainerEntity");
        LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        lcemfb.setLoadTimeWeaver(loadTimeWeaver);
        return lcemfb;
    }
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/500words?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}