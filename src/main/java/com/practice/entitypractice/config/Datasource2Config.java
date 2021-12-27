//package com.practice.entitypractice.config;
//
//import com.practice.entitypractice.data.person.Humanoid;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//
////import javax.sql.DataSource;
//
//@Configuration
//@EnableJpaRepositories(basePackageClasses = Humanoid.class, entityManagerFactoryRef = "humanoidFactory")
//public class Datasource2Config {
//
////    @Bean
////    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
////        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(),
////                null);
////    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties("spring.datasource2")
//    public DataSourceProperties getDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource2.configuration")
//    public DataSource getDatasource2(DataSourceProperties properties) {
//        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "humanoidFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean humanoidEntityManagerFactory
//            (EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
//        return entityManagerFactoryBuilder
//                .dataSource(getDatasource2(getDataSourceProperties()))
//                .packages(Humanoid.class)
//                .persistenceUnit("humanoid")
//                .build();
//    }
//
//    @Bean(name = "humanoidTx")
//    @Primary
//    public PlatformTransactionManager humanoidTransactionManager
//            (@Qualifier("humanoidFactory")EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
