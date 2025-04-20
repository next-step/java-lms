package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
public class JdbcCourseRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @DisplayName("강좌 저장 테스트")
    @Test
    void testSave() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        assertDoesNotThrow(() -> courseRepository.save(course.toCourseEntity()));
    }

    @DisplayName("강좌 조회 테스트")
    @Test
    void testFindById() {
        Course course = new Course("TDD, 클린 코드 with Java 2", 2L);
        long generatedId = courseRepository.save(course.toCourseEntity());
        assertThat(courseRepository.findById(generatedId)).isNotNull();
    }
}
