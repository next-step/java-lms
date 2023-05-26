package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nextstep.fixtures.CourseFixtures.*;
import static nextstep.fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    @DisplayName("Course 생성 후 Session add")
    @Test
    void create() {
        Course course = testCourse1()
                .sessions(new ArrayList<>())
                .build();

        course.addSession(testSession1());
        course.addSession(testSession2());

        assertThat(course.getSessions()).hasSize(2);
    }
}
