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
        int count = sessionRepository.save(SessionTest.FREE_S1);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(SessionTest.FREE_S1.getId());
        assertThat(SessionTest.FREE_S1.getId()).isEqualTo(savedSession.getId());
    }
}
