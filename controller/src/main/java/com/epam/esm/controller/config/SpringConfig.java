package com.epam.esm.controller.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@PropertySource(value = "classpath:application.properties")
public class SpringConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public BasicDataSource mysqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("datasource.driverClassName"));
        dataSource.setUrl(environment.getProperty("datasource.url"));
        dataSource.setUsername(environment.getProperty("datasource.username"));
        dataSource.setPassword(System.getenv("DB_PASSWORD_DEV"));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("datasource.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("datasource.maxActive")));
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(BasicDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
