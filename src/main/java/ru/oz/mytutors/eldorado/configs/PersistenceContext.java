package ru.oz.mytutors.eldorado.configs;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories(basePackages = {
        "ru.oz.mytutors.eldorado.repository"
})
@Configuration
public class PersistenceContext {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/eldo?useSSL=true")
                .username("root")
                .password("123456")
                .build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/schema.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/facet.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/tag.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/facet-value.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/attribute.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/attribute-value.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/ranged-attribute-values.sql"));
        resourceDatabasePopulator.addScript(new ClassPathResource("/init/value_to_facet-value.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("ru.oz.mytutors.eldorado.model");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(additionalProperties());

        return emf;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
//        pro  perties.setProperty("spring.jpa.database", "MYSQL");
//        properties.setProperty("spring.jpa.hibernate.ddl-auto", "create");
//        properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty("spring.jpa.hibernate.naming-strategy", "org.hibernate.cfg.DefaultNamingStrategy");

        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.physical_naming_strategy", "ru.oz.mytutors.PhysicalNamingStrategyImpl");
        properties.setProperty("hibernate.show_sql", "true");


//        properties.setProperty("spring.jpa.hibernate.naming.strategy", "org.hibernate.cfg.Hibernate5NamingStrategy");
//        properties.setProperty("spring.jpa.hibernate.naming.strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

//                env.getProperty("mysql-hibernate.hbm2ddl.auto"));
//        properties.setProperty("hibernate.dialect",
//                env.getProperty("mysql-hibernate.dialect"));
//        properties.setProperty("hibernate.show_sql",
//                env.getProperty("mysql-hibernate.show_sql") != null
//                        ? env.getProperty("mysql-hibernate.show_sql") : "false");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

}
