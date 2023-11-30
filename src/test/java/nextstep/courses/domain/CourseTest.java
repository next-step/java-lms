package nextstep.courses.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTest.SESSION_모집중_10명_1000원;
import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    @DisplayName("과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.")
    void canHasManySessions() {
        Course course = new Course("객체지향 프로그래밍", 1L);
        Session session = SESSION_모집중_10명_1000원();
        course.addSession(session);
        assertThat(course.contains(session)).isTrue();
    }


}
