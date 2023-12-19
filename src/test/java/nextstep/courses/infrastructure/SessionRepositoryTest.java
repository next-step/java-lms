package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionStatus;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = new Session(1L, CourseTest.C1,100000L, SessionPaymentType.PAID, new NsUsers(List.of(
                NsUserTest.SANJIGI)),15,new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1)),
                SessionStatus.ENROLLING,new CoverImage("pobi.png",500L,300D, 200D));
        int count = sessionRepository.save(session.toDto());
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(savedSession.isSameId(1L)).isTrue();
        LOGGER.debug("Session: {}", savedSession);
    }
}
