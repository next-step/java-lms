package nextstep.courses.domain;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CourseTest {

    @Test
    void Course_생성자_기본() {
        new Course();
    }

    @Test
    void Course_생성자_강의() {
        Session session = new Session();
        Course course = new Course(session);

        Assertions.assertThat(course)
                .isNotNull()
                .extracting("sessions")
                .asList()
                .containsExactly(session);
    }

    @Test
    void Course_생성자_강의리스트() {
        Session session1 = new Session();
        Session session2 = new Session();
        Course course = new Course(List.of(session1, session2));

        Assertions.assertThat(course)
                .isNotNull()
                .extracting("sessions")
                .asList()
                .containsExactly(session1, session2);
    }
}
