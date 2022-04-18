package com.multi.datasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multi.datasource.repo",entityManagerFactoryRef = "ormEmf",transactionManagerRef = "ormTxn")
public class ORMDB {

    @Autowired
    CatalogDBConfig config;


    @Primary
    @Bean("hikariCpOrm")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(config.getDbUrlOrm());
        dataSource.setUsername(config.getDbUsername());
        dataSource.setPassword(config.getDbPassword());
        dataSource.setPoolName("HACKATHON_ORM");
        dataSource.setMaximumPoolSize(100);
        dataSource.setMinimumIdle(1222);
        dataSource.setMaxLifetime(50000);
        dataSource.setConnectionTimeout(50000);
        dataSource.setIdleTimeout(30000);

        dataSource.setAutoCommit(true);
        return dataSource;
    }

    @Primary
    @Bean("ormEmf")
    public LocalContainerEntityManagerFactoryBean factoryBean() {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.multi.datasource.entity");
        factoryBean.setJpaProperties(additionalProperties());
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        return factoryBean;
    }

    @Primary
    @Bean("ormTxn")
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager jpadataSourceTransactionManager = new JpaTransactionManager();
        jpadataSourceTransactionManager.setEntityManagerFactory(factoryBean().getObject());
        jpadataSourceTransactionManager.setDataSource(dataSource());
        return jpadataSourceTransactionManager;
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.setProperty("hibernate.jdbc.time_zone", "Asia/Kolkata");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");

        //hibernateProperties.setProperty("hibernate.show_sql","yes");
        return hibernateProperties;
    }
}