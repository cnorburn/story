package com.aerian.bbc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.aerian.bbc")
@EnableTransactionManagement
public class JPAConfigOne {
    @Bean
    public JpaTransactionManager jpaTransMan(){
        JpaTransactionManager jtManager = new JpaTransactionManager(
                getEntityManagerFactoryBean().getObject());
        return jtManager;
    }
    @Bean
    public LocalEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();
        lemfb.setPersistenceUnitName("localEntity");
        return lemfb;
    }
}