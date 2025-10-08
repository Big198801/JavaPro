package ru.big198801.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
    @Value("${postgresql.username}")
    private String username;
    @Value("${postgresql.password}")
    private String password;
    @Value("${postgresql.driver}")
    private String driver;
    @Value("${postgresql.pool}")
    private int maxPoolSize;

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(driver);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        return new HikariDataSource(hikariConfig);
    }
}
