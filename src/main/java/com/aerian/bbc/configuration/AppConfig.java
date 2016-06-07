package com.aerian.bbc.configuration;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@Import(JPAConfig.class)
public class AppConfig {

    @Bean
    public static PropertyPlaceholderConfigurer PropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        configurer.setIgnoreResourceNotFound(true);
        List<Resource> resources = new ArrayList<Resource>();
        resources.add(new ClassPathResource("application.properties"));
        resources.add(new ClassPathResource("profile.properties"));
        configurer.setLocations(resources.toArray(new Resource[resources.size()]));
        return configurer;
    }

}