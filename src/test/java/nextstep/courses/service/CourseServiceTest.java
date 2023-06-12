package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class CourseServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(
                new JdbcCourseRepository(jdbcTemplate),
                new JdbcSessionRepository(jdbcTemplate));
    }

    @DisplayName("Course 초기화 테스트 : Course에 포함된 Session들을 DB로부터 조회하여 객체를 초기화한다.")
    @Test
    void findTest() {
        Course course = courseService.findCourseById(0L);
        assertThat(course.getTitle()).isEqualTo("넥스트스텝");
        assertThat(course.findSession(0L).getTitle()).isEqualTo("TDD 클린코드");
    }
}