package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.Session.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    @DisplayName("Course는 여러개의 Session을 가질 수 있다.")
    void courseHasSessions() {
        Course course = new Course();
        Session javaSession = new Session();
        Session pythonSession = new Session();

        course.addSession(javaSession);
        course.addSession(pythonSession);

        System.out.println(course.getSessions().getSessions().toString());

        assertThat(course.getSessions().getSessions()).hasSize(2);
    }
}
