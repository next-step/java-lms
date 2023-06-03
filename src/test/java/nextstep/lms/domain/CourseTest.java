package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    @DisplayName("과정 개설")
    void createCourseTest() {
        Session session1 = Session.createSession(
                LocalDate.of(2023, 5, 3),
                LocalDate.of(2023, 5, 5),
                0L,
                SessionPaidType.PAID,
                5
        );

        Session session2 = Session.createSession(
                LocalDate.of(2023, 5, 6),
                LocalDate.of(2023, 5, 10),
                1L,
                SessionPaidType.FREE,
                10
        );

        List<Session> sessions = List.of(session1, session2);
        Course course = new Course(sessions);

        assertThat(course)
                .isInstanceOf(Course.class);
    }

}
