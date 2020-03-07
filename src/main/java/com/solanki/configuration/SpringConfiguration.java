package com.solanki.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfiguration {

	@Autowired
	private Environment environment;
	
	public DataSource empDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource1.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource1.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource1.password"));
		return dataSource;
	}
	
	public DataSource userDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource2.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource2.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource2.password"));
		return dataSource;
	}
}
