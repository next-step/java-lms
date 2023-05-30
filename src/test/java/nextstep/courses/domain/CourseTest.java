package nextstep.courses.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {
    @DisplayName("과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다.")
    @Test
    void test() {
        Course course = new Course();
        Session firstSession = new Session(1);
        Session secondSession = new Session(2);
        Session thirdSession = new Session(3);

        Sessions sessions = new Sessions();
        sessions.add(firstSession);
        sessions.add(secondSession);
        sessions.add(thirdSession);

        //when
        course.updateSessions(sessions);

        //then
        assertThat(course.hasSession(firstSession)).isTrue();
        assertThat(course.hasSession(secondSession)).isTrue();
        assertThat(course.hasSession(thirdSession)).isTrue();
    }
}