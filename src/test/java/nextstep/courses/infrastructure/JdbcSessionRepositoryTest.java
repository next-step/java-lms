package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void save() throws PeriodException {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        Course course = new Course("title", 1L, 1);
        Session session = new Session(period, SessionStatus.OPEN, new Students(), new SessionType(PayType.FREE, 1000L, 10), new SessionImage());
        session.addCourse(course);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session save = sessionRepository.findById(1L);
        assertThat(save.status()).isEqualTo(session.status());
    }
}