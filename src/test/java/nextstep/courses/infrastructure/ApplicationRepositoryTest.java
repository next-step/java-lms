package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ApplicationRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ApplicationRepository applicationRepository;

    @BeforeEach
    void setUp() {
        applicationRepository = new JdbcApplicationRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Application application = new Application(1L, 1L);
        int savedId = applicationRepository.save(application);
        assertThat(savedId).isEqualTo(1);
        Application savedApplication = applicationRepository.findById(1L);
        assertThat(application.getUserId()).isEqualTo(savedApplication.getUserId());
        assertThat(application.getSessionId()).isEqualTo(savedApplication.getSessionId());
        assertThat(application.getStatus()).isEqualTo(savedApplication.getStatus());
        LOGGER.debug("Application: {}", savedApplication);
    }
}
