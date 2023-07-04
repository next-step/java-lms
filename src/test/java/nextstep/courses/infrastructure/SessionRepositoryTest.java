package nextstep.courses.infrastructure;

import nextstep.courses.domain.LectureStatus;
import nextstep.courses.domain.LectureType;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.SessionBuilder.session;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = session()
                .build();
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);

        assertThat(session.getId()).isEqualTo(savedSession.getId());
        assertThat(session.getLectureType()).isEqualTo(LectureType.FREE);
        assertThat(session.getLectureStatus()).isEqualTo(LectureStatus.PREPARING);
        assertThat(session.getMaxUser()).isEqualTo(100);
    }
}
