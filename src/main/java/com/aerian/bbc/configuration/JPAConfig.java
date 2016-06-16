package com.aerian.bbc.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.*;
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
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(getDataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setPackagesToScan("com.aerian.bbc");
        return lef;

    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
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