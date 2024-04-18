package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionTest;

class CourseTest {

    @Test
    @DisplayName("새로운 강의를 추가한다.")
    void Add_NewSession() {
        final Course course = new Course(
                1L,
                "backend",
                1L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 1, 1, 0, 0),
                new Sessions()
        );
        final Session session = SessionTest.session();

        course.addNewSession(session);

        assertThat(session.course())
                .isEqualTo(course);
    }
}
