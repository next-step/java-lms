package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nextstep.Fixtures.aCourse;
import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    @DisplayName("과정은 여러개의 강의를 가질 수 있다.")
    void test01() {
        Course course = aCourse().withSessions(new ArrayList<>()).build();

        course.addSession(new Session());
        course.addSession(new Session());

        assertThat(course.getSessions()).hasSize(2);
    }
}
