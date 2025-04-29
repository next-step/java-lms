package nextstep.courses.integration;

import nextstep.courses.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class CourseServiceIntegrationTest {

    @Autowired
    private CourseService courseService;

    @Transactional
    @Test
    void testCreateCourse() {
        String title = "Test Course";
        Long creatorId = 1L;

        assertDoesNotThrow(() -> courseService.createCourse(title, creatorId));
    }

    @Transactional
    @Test
    void testDeleteCourse() {
        String title = "Test Course";
        Long creatorId = 1L;
        long savedId = courseService.createCourse(title, creatorId);

        assertDoesNotThrow(() -> courseService.deleteCourse(savedId));
    }
}
