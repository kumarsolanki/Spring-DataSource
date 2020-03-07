package com.solanki.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "empEntityManagerFactory", basePackages = {
		"com.solanki.employee.repository" }, transactionManagerRef = "empTransactionManager")
public class EmpDbConfig {

	@Primary
	@Bean(name = "empDataSource")
	@ConfigurationProperties(prefix = "spring.datasource1")
	public DataSource userDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "empEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("empDataSource") DataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hibernate.hbm2ddl.auto", "create-drop");
		map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		map.put("show-sql", true);
		return builder.dataSource(dataSource).properties(map).packages("com.solanki.employee.beans")
				.persistenceUnit("Employee").build();

	}

	@Primary
	@Bean(name = "empTransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("empEntityManagerFactory") EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

}
