package nextstep.session.infrastructure;

import nextstep.courses.domain.Price;
import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionRepository sessionRepository;

    private JdbcStudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new JdbcStudentRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, studentRepository);
    }

    @Test
    @DisplayName("CRUD 테스트")
    void sessionCrudTest() {
        Session session = new Session(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2),
                "url",
                Session.BillType.FREE,
                new Price(0L),
                100L,
                0L
        );
        int cnt = sessionRepository.save(session);

        Session savedSession = sessionRepository.findById(3L);

        assertThat(cnt).isEqualTo(1);
        assertThat(savedSession.getCourseId()).isEqualTo(session.getCourseId());
    }
}
