package nextstep.course.domain;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.support.TestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class CourseRepositoryTest extends TestSupport {

    @Autowired
    private CourseRepository courseRepository;

    @DisplayName("과정을 저장한 후 ID로 강의를 조회한다.")
    @Test
    void saveTest() {
        Course course = new Course("TDD클린코드", 1L, 1L);

        courseRepository.save(course);

        assertThat(courseRepository.findById(1L))
                .extracting("id", "title", "term", "creatorId")
                .contains(1L, "TDD클린코드", 1L, 1L);
    }

    @DisplayName("과정을 전체 조회한다.")
    @Test
    void findByIdTest() {
        Course course1 = new Course("TDD클린코드1", 1L, 1L);
        Course course2 = new Course("TDD클린코드2", 1L, 1L);

        courseRepository.save(course1);
        courseRepository.save(course2);

        assertThat(courseRepository.findAll())
                .extracting("title", "term", "creatorId")
                .containsExactly(
                        tuple("TDD클린코드1", 1L, 1L),
                        tuple("TDD클린코드2", 1L, 1L)
                );
    }
}
