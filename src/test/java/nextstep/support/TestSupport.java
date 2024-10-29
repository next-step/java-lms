package nextstep.support;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@SpringBootTest
@Transactional
public abstract class TestSupport {

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUpDatabase() {
        runDropSchemaScript();
        runSchemaScript();
        runDataSchemaScript();
    }

    private void runSchemaScript() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.execute(dataSource);
    }

    private void runDataSchemaScript() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data.sql"));
        populator.execute(dataSource);
    }

    private void runDropSchemaScript() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("drop.sql"));
        populator.execute(dataSource);
    }

}