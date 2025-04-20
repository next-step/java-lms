package nextstep.courses.domain;

import nextstep.courses.domain.model.Course;
import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    public static Course createCourse() {
        return new Course("과정명", 1L);
    }

    @Test
    @DisplayName("과정(Course)은 여러 개의 강의(Session)를 가질 수 있다.")
    void courseHaveSession() {
        Course course = createCourse();
        Session session = SessionTest.createFreeSession(SessionStatus.OPEN);
        course.addSession(session);
        assertThat(course.getSessions()).contains(session);
    }

}
