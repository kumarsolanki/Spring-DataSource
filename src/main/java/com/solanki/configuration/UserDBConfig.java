package com.solanki.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.solanki.user.repository" })
public class UserDBConfig {

	@Autowired
	private Environment environment;

//	public DataSource empDataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl(environment.getProperty("spring.datasource1.url"));
//		dataSource.setUsername(environment.getProperty("spring.datasource1.username"));
//		dataSource.setPassword(environment.getProperty("spring.datasource1.password"));
//		return dataSource;
//	}

	@Bean(name = "userDataSource")
	@ConfigurationProperties(prefix = "spring.datasource2")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("userDataSource") DataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto", "create-drop");
		map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		map.put("show-sql", true);
		return builder.dataSource(dataSource).properties(map).packages("com.solanki.employee.beans.user")
				.persistenceUnit("User").build();

	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

}
