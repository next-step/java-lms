package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DisplayName("데이터베이스 초기화")
    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DELETE FROM course");
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
    }

    @DisplayName("Course 저장 테스트")
    @Test
    void save() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);

        long savedId = courseRepository.save(course);

        assertThat(savedId).isPositive();
    }

    @DisplayName("Course Id로 찾기 테스트")
    @Test
    void findById() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long savedId = courseRepository.save(course);

        Course foundCourse = courseRepository.findById(savedId);

        assertThat(foundCourse).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(course);
    }

    @DisplayName("Course 수정 테스트")
    @ParameterizedTest
    @CsvSource({"Updated Title", "Modified Title", "Revised Title"})
    void update(String updatedTitle) {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long savedId = courseRepository.save(course);

        Course updatedCourse = new Course(savedId, updatedTitle, course.getCreatorId(), course.getCreatedAt(), course.getUpdatedAt());
        int updatedCount = courseRepository.update(updatedCourse);

        assertThat(updatedCount).isEqualTo(1);
        Course retrievedCourse = courseRepository.findById(savedId);
        assertThat(retrievedCourse.getTitle()).isEqualTo(updatedTitle);
    }

    @DisplayName("Course 삭제 테스트")
    @Test
    void delete() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long courseId = courseRepository.save(course);

        int deletedRows = courseRepository.delete(courseId);

        assertAll(
                () -> assertThat(deletedRows).isEqualTo(1),
                () -> assertThrows(EmptyResultDataAccessException.class, () -> courseRepository.findById(courseId))
        );
    }

}
