package by.epam.kvirykashvili.javalabtask.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@Configuration
@PropertySource({
        "classpath:db/migration/V1.1__createDatabaseScript.sql",
        "classpath:db/migration/V1.2__generateDatabaseDataScript.sql"
})
public class TestAppConfig {

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        final FlywayPreparer preparer = FlywayPreparer.forClasspathLocation("db/migration");
        DataSource embeddedDataSource = null;
        try {
            EmbeddedPostgres embeddedPostgres = EmbeddedPostgres.start();
            embeddedDataSource = embeddedPostgres.getPostgresDatabase();
            preparer.prepare(embeddedDataSource);
        } catch (SQLException | IOException e) {
            log.error("Test DataSource initialization error!", e);
        }
        return embeddedDataSource;
    }
}
