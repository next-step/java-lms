package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourseRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);
        Course savedCourse = courseRepository.findById(1L);
        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
    }

    @Test
    @DisplayName("여러 Session들이 포함된 Course일때 Course 조회 시에 모든 Session들도 같이 조회된다")
    void shouldFindAllSessionsIfContainsSessions() {
        sessionRepository.save(new Session(100L, 100, SessionStatus.RECRUITING.name(), LocalDate.of(2024, 3, 10), LocalDate.of(2024, 4, 10), 1L));
        sessionRepository.save(new Session(213L, 123, SessionStatus.CLOSED.name(), LocalDate.of(2024, 2, 9), LocalDate.of(2024, 3, 9), 1L));
        sessionRepository.save(new Session(523L, 65, SessionStatus.PREPARING.name(), LocalDate.of(2024, 5, 12), LocalDate.of(2024, 6, 12), 1L));
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);
        Course savedCourse = courseRepository.findById(1L);
        assertThat(savedCourse.getTitle()).isEqualTo(savedCourse.getTitle());
        assertThat(savedCourse.getSessions().size()).isEqualTo(3);
    }
}
