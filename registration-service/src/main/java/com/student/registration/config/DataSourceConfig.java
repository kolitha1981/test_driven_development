package com.student.registration.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
	
	@Value("${registration.service.datasource.driver.class}")
	private String driverClassName;
	@Value("${registration.service.datasource.url}")
	private String dataSourceURL;
	@Value("${registration.service.datasource.username}")
	private String dataSourceUserName;
	@Value("${registration.service.datasource.password}")
	private String dataSourcePassword;

	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dataSourceURL);
        dataSource.setUsername(dataSourceUserName);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }
}
