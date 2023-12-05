package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.SessionState;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.FreeSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class FreeSessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreeSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;
    private CoverImageRepository coverImageRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        FreeSession freeSession = new FreeSession(
                coverImageRepository.findById(2L),
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 29),
                SessionState.PREPARING,
                LocalDateTime.now()
        );
        int count = freeSessionRepository.save(courseRepository.findById(2L), freeSession);
        assertThat(count).isEqualTo(1);

        FreeSession saveSession = freeSessionRepository.findById(1L);
        assertThat(freeSession.id()).isEqualTo(saveSession.id());
        LOGGER.debug("FreeSession : {}", saveSession);
    }

}
