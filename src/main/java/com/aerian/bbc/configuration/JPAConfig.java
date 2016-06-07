package com.aerian.bbc.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
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
public class JPAConfig {

    @Value("${hibernate.hbm2ddl.auto}")
    private String jpaHibernateHbm2ddlAuto;

    @Value("${hibernate.dialect}")
    private String jpaHibernateDialect;

    @Value("${hibernate.adapterVendor}")
    private String jpaHibernateAdapterVendor;

    @Value("${database.driverClassName}")
    private String dataSourceDriverClassName;

    @Value("${database.url}")
    private String dataSourceUrl;

    @Value("${database.username}")
    private String dataSourceUsername;

    @Value("${database.password}")
    private String dataSourcePassword;

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
        dataSource.setDriverClassName(this.dataSourceDriverClassName);
        dataSource.setUrl(this.dataSourceUrl);
        dataSource.setUsername(this.dataSourceUsername);
        dataSource.setPassword(this.dataSourcePassword);
        return dataSource;
    }
}