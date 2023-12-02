package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.CourseTest.course;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GroupTest {
    @Test
    @DisplayName("기수별로 과정을 가질 수 있다.")
    void haveCourseTest() {
        Group group = new Group(course, 17L);

        assertThat(group.getCourse().getTitle()).isEqualTo("TDD with java");
    }
}
