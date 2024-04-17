package nextstep.courses.domain;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CourseTest {

    @Test
    void Course_생성자_기본() {
        new Course();
    }

    @Test
    void Course_생성자_강의() {
        Session session = new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING);
        Course course = new Course(session);

        Assertions.assertThat(course)
                .isNotNull()
                .extracting("sessions")
                .asList()
                .containsExactly(session);
    }

    @Test
    void Course_생성자_강의리스트() {
        Session session1 = new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING);
        Session session2 = new FreeSession(0L, "title", LocalDate.now(), LocalDate.now(), new CoverImage(), SessionStatus.PREPARING);
        Course course = new Course(List.of(session1, session2));

        Assertions.assertThat(course)
                .isNotNull()
                .extracting("sessions")
                .asList()
                .containsExactly(session1, session2);
    }
}
