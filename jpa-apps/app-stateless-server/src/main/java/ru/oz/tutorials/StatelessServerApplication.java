package ru.oz.tutorials;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.InputStream;

@SpringBootApplication
public class StatelessServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatelessServerApplication.class, args);
    }

    @Autowired
    DataSource dataSource;

    @Bean
    CommandLineRunner initDb() {
        return (args) -> {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("testdata.xml");

            IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
            connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

            DatabaseOperation.CLEAN_INSERT.execute(connection, new FlatXmlDataSetBuilder().build(input));
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
            } else {
                System.err.println("Can't import test data, check connection settings!");
            }
        };
    }
}
