package nextstep.courses.infrastructure.course;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
class CourseRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void init() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    @Transactional
    @DisplayName("새로운 과정을 생성한다.")
    void Save_NewCourse() {
        final CourseEntity courseEntity = new CourseEntity("TDD, 클린 코드 with Java", 1L);

        final long savedCourseEntityId = courseRepository.save(courseEntity);
        final CourseEntity savedCourseEntity = courseRepository.findById(savedCourseEntityId).get();

        assertThat(savedCourseEntity.title())
                .isEqualTo(courseEntity.title());
    }
}
