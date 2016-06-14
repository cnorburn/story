package com.aerian.bbc.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Configuration
@ComponentScan("com.aerian.bbc")
@EnableTransactionManagement
public class JPATestConfig {

    @Value("${hibernate.adapterVendor}")
    private String jpaHibernateAdapterVendor;

    @Value("org.hsqldb.jdbcDriver")
    private String dataSourceDriverClassName;

    @Value("jdbc:hsqldb:mem:Story")
    private String dataSourceUrl;

    @Value("sa")
    private String dataSourceUsername;

    @Value("")
    private String dataSourcePassword;


    /**
     * Bootstraps an in-memory HSQL database.
     */
    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.dataSourceDriverClassName);
        dataSource.setUrl(this.dataSourceUrl);
        dataSource.setUsername(this.dataSourceUsername);
        dataSource.setPassword(this.dataSourcePassword);
        return dataSource;
    }

    /**
     * Picks up entities from the project's base package.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getClass().getPackage().getName());
        factory.setDataSource(getDataSource());
        factory.setPackagesToScan("com.aerian.bbc");

        return factory;
    }

    @Bean
    public PlatformTransactionManager jpaTransMan() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());
        return txManager;
    }

    @Bean
    @Lazy(false)
    public ResourceDatabasePopulator populateDatabase() throws SQLException {

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("test/resources/sql/data.sql"));

        Connection connection = null;

        try {
            connection = DataSourceUtils.getConnection(getDataSource());
            populator.populate(connection);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, getDataSource());
            }
        }
        return populator;
    }


}
