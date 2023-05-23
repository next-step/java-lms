package nextstep.courses.domain;

import nextstep.courses.SessionFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CourseTest {
    private Course course;
    private List<Session> sessions = new ArrayList<>();

    @Test
    void 한_과정에_여러_강의_추가_테스트() {
        SessionFixture sessionFixture = new SessionFixture();
        sessions.add(sessionFixture.강의_과정_1());
        sessions.add(sessionFixture.강의_과정_2());

        course = new Course(sessions);

        Assertions.assertThat(course.getSessions().size()).isEqualTo(2);

    }
}