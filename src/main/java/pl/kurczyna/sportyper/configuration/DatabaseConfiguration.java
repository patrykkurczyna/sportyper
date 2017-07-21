package pl.kurczyna.sportyper.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.sql.SQLException;

import liquibase.Liquibase;
import liquibase.database.DatabaseConnection;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    @Autowired
    public SpringLiquibase liquibase(DataSource configuredDataSource) {
        return new SpringLiquibase() {

            @PostConstruct
            public void init() throws LiquibaseException, SQLException {
                ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();

                DatabaseConnection connection = new JdbcConnection(configuredDataSource.getConnection());

                Liquibase liquibase = new Liquibase("db-changelog.yml", resourceAccessor, connection);
                liquibase.getDatabase().setDatabaseChangeLogTableName("sportyper_dbchangelog");
                liquibase.getDatabase().setDatabaseChangeLogLockTableName("sportyper_dbchangeloglock");
                liquibase.update("");
            }

            @Override
            public void afterPropertiesSet() throws LiquibaseException {
                // empty
            }
        };
    }

}
