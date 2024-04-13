package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    private JdbcCourseRepository courseRepository;

    private JdbcSessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("CRUD 테스트")
    @Test
    void crud() {
        courseRepository.save(CourseTest.C1);
        Session session = new PaidSession(0L, CourseTest.C1.getId(), 10L, 10_000L, List.of(SessionCoverImageTest.CI), SessionType.PAID);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(0L);
        assertThat(session.getAmount()).isEqualTo(savedSession.getAmount());
    }
}
