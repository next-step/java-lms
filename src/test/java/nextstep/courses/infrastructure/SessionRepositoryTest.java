package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = Session.builder()
                .courseId(1L)
                .title("강의1")
                .period(new Period(LocalDateTime.now().plusHours(2)))
                .imageUrl("http://image.com")
                .sessionRecruitState(SessionRecruitState.UN_RECRUITING)
                .sessionState(SessionState.PREPARING)
                .sessionType(SessionType.PAY)
                .creatorId(1L)
                .students(new Students())
                .build();
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        LOGGER.debug("Course: {}", savedSession);
    }
}
