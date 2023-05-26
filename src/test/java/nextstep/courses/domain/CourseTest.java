package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nextstep.Fixtures.CourseFixtures.*;
import static nextstep.Fixtures.SessionFixtures.testSession1;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @DisplayName("Course 생성 후 Session add")
    @Test
    void create() {
        Course course = testCourse1()
                .sessions(new ArrayList<>())
                .build();

        course.addSession(testSession1().build());
        course.addSession(testSession1().build());

        assertThat(course.getSessions()).hasSize(2);
    }
}
