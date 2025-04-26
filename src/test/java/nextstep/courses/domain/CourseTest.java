package nextstep.courses.domain;

import nextstep.courses.domain.model.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {
    public static Course COURSE1 = new Course(1L, "넥스트 스텝", false, 1L, LocalDateTime.now(), LocalDateTime.now());

    public static Course createCourse() {
        return new Course("과정명", false, 1L);
    }

    public static Course createCourseWithSelection() {
        return new Course("과정명", true, 1L);
    }


    @Test
    @DisplayName("과정(Course)은 선발 절차를 포함할 수 있다.")
    void courseHaveSelectionProcess() {
        Course course = new Course("과정명", true, 1L);
        assertThat(course.hasSameSelection(new Course("테스트", true, 1L))).isTrue();
    }

}
