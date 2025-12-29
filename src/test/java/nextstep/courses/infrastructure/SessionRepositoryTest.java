package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.enrollment.Money;
import nextstep.courses.domain.session.Session;
import nextstep.courses.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = SessionTestBuilder.aSession()
                .withCapacity(10)
                .withPaidEnrollment(new Money(5000L))
                .build();
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getCoverImageName()).isEqualTo(savedSession.getCoverImageName());
        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void findByCourseId() {
        Session session = SessionTestBuilder.aSession()
                .withId(1L)
                .withCourseId(10L)
                .withCapacity(10)
                .withPaidEnrollment(new Money(5000L))
                .build();
        int count = sessionRepository.save(session);

        session = SessionTestBuilder.aSession()
                .withId(2L)
                .withCourseId(10L)
                .withCapacity(10)
                .withPaidEnrollment(new Money(5000L))
                .build();
        count += sessionRepository.save(session);

        assertThat(count).isEqualTo(2);

        List<Session> sessions = sessionRepository.findByCourseId(10L);
        assertThat(sessions).hasSize(2);
        LOGGER.debug("Sessions: {}", sessions);
    }
}
