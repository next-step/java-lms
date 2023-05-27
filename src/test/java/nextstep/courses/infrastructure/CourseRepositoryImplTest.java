package nextstep.courses.infrastructure;

import java.time.LocalDateTime;
import nextstep.courses.domain.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CourseRepositoryImplTest {

    private final CourseRepositoryImpl courseRepository;

    public CourseRepositoryImplTest() {
        this.courseRepository = new CourseRepositoryImpl();
    }

    @Test
    void saveTest() {
        Course course = new Course("java", 1L);
        int save = courseRepository.save(course);

        Assertions.assertThat(save).isEqualTo(1);
    }

    @Test
    void findByIdTest() {
        String title = "java";
        Long creatorId = 1L;
        Course newCourse = new Course(title, creatorId);
        int save = courseRepository.save(newCourse);

        Course course = courseRepository.findById((long) save);

        Assertions.assertThat(course.getTitle()).isEqualTo(title);
        Assertions.assertThat(course.getCreatorId()).isEqualTo(creatorId);

    }
}
