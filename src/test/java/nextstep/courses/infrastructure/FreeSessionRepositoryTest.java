package nextstep.courses.infrastructure;

import nextstep.courses.domain.Duration;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Images;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.courses.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class FreeSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FreeSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;

    @BeforeEach
    void setUp() {
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
    }

    @Test
    void create() {
        FreeSession session = new FreeSession(5L, duration(), images(), SessionStatus.RECRUITING, LocalDateTime.now(), null);
        int count = freeSessionRepository.save(session, 2L);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void read() {
        FreeSession savedSession = freeSessionRepository.findById(3L);
        FreeSession session = new FreeSession(3L, duration(), images(), SessionStatus.RECRUITING, LocalDateTime.now(), null);
        assertThat(savedSession).isEqualTo(session);
        LOGGER.debug("FreeSession: {}", savedSession);
    }

    private Images images() {
        Image image1 = new Image(3L, 0.3, "GIF", 300, 200, LocalDateTime.now(), null);
        Image image2 = new Image(4L, 1.0, "JPG", 300, 200, LocalDateTime.now(), null);
        return new Images(Arrays.asList(image1, image2));
    }

    private Duration duration() {
        return new Duration(LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 1));
    }
}
