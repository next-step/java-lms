package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionTest;

public class CourseTest {

    @Test
    @DisplayName("새로운 강의를 추가한다.")
    void Add_NewSession() {
        final Course course = course();
        final Session session = SessionTest.session();

        course.addNewSession(session);

        assertThat(session.course())
                .isEqualTo(course);
    }

    public static Course course() {
        return new Course("TDD, 클린 코드 with Java", 1L);
    }
}
