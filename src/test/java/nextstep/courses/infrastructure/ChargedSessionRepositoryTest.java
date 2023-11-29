package nextstep.courses.infrastructure;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.Images;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.repository.ChargedSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ChargedSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargedSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChargedSessionRepository chargedSessionRepository;

    @BeforeEach
    void setUp() {
        chargedSessionRepository = new JdbcChargedSessionRepository(jdbcTemplate);
    }

    @Test
    void create() {
        ChargedSession session = new ChargedSession(6L, duration(), images(), SessionStatus.RECRUITING, 0, BigDecimal.valueOf(10_000), LocalDateTime.now(), null);
        int count = chargedSessionRepository.save(session, 2L);
        assertThat(count).isEqualTo(1);
    }

    @Test
    void read() {
        ChargedSession savedSession = chargedSessionRepository.findById(2L);
        ChargedSession session = new ChargedSession(2L, duration(), images(), SessionStatus.RECRUITING, 100, BigDecimal.valueOf(1_0000), LocalDateTime.now(), null);
        assertThat(savedSession).isEqualTo(session);
        LOGGER.debug("ChargedSession: {}", savedSession);
    }

    private Images images() {
        Image image = new Image(2L, 0.3, "GIF", 300, 200, LocalDateTime.now(), null);
        return new Images(Arrays.asList(image));
    }

    private Duration duration() {
        return new Duration(LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 1));
    }

}
