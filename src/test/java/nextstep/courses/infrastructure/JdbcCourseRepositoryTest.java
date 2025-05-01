package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.entity.CourseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

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

    @DisplayName("강좌 저장")
    @Test
    void testSave() {
        assertDoesNotThrow(() -> courseRepository.save(createCourseEntity(1L)));
    }

    @DisplayName("강좌 삭제")
    @Test
    void testDelete() {
        long generatedId = courseRepository.save(createCourseEntity(1L));

        assertDoesNotThrow(() -> courseRepository.delete(generatedId));
    }

    private CourseEntity createCourseEntity(long courseId) {
        return CourseEntity.builder()
            .id(courseId)
            .title("test-title")
            .creatorId(3L)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();
    }
}
