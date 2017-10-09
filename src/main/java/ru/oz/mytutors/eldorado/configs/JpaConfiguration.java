package ru.oz.mytutors.eldorado.configs;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
public class JpaConfiguration {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username("root")
                .password("123456")
                .url("jdbc:mysql://localhost:3306/eldo?useSSL=true")
                .build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/tag.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/facet-value.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/attribute.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/attribute-value.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/ranged-attribute-values.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "ru.oz.mytutors.eldorado.model" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("spring.jpa.database", "MYSQL");
        properties.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("spring.jpa.hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

}
