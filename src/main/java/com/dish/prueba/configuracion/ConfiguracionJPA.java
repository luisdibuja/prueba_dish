package com.dish.prueba.configuracion;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.dish.prueba.repositorios")
@EnableTransactionManagement
public class ConfiguracionJPA {

	private final Environment environment;

	public ConfiguracionJPA(Environment environment) {
		this.environment = environment;
	}

	@Value("${datasource.pruebadish.maxPoolSize:10}")
	private int maxPoolSize;

	/*
	 * Se toman propiedades de .yml
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.pruebadish")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}

	/*
	 * Configuración de HikariCP pooled DataSource.
	 */
	@Bean
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
			HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
					.create(dataSourceProperties.getClassLoader())
					.driverClassName(dataSourceProperties.getDriverClassName())
					.url(dataSourceProperties.getUrl())
					.username(dataSourceProperties.getUsername())
					.password(dataSourceProperties.getPassword())
					.type(HikariDataSource.class)
					.build();
			dataSource.setMaximumPoolSize(maxPoolSize);
			return dataSource;
	}

	/*
	 * Configuración del Entity Manager Factory.
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.dish.prueba.modelo");
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}

	/*
	 * Adaptador especìfico del proveedor.
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	/*
	 * Aquí se pueden especificar propiedades específicas del adaptador.
	 */
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.pruebadish.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.pruebadish.hibernate.hbm2ddl.method"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.pruebadish.hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.pruebadish.hibernate.format_sql"));
		if(StringUtils.isNotEmpty(environment.getRequiredProperty("datasource.pruebadish.defaultSchema"))){
			properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.pruebadish.defaultSchema"));
		}
		return properties;
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

}
