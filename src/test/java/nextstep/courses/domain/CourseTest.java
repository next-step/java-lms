package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import nextstep.Session.Image;
import nextstep.Session.Session;
import nextstep.Session.SessionDuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    @DisplayName("Course는 여러개의 Session을 가질 수 있다.")
    void courseHasSessions() {
        LocalDateTime started = LocalDateTime.of(2023, 12, 01, 0, 0, 0);
        LocalDateTime ended = LocalDateTime.of(2023, 12, 31, 0, 0, 0);

        Course course = new Course();
        Session javaSession =
            Session.createFreeSession("자바강의", new Image(), new SessionDuration(started, ended));
        Session pythonSession =
            Session.createPaidSession("자바강의", new Image(), new SessionDuration(started, ended), 100,
                10);

        course.addSession(javaSession);
        course.addSession(pythonSession);

        System.out.println(course.getSessions().getSessions().toString());

        assertThat(course.getSessions().getSessions()).hasSize(2);
    }
}
