package nextstep.courses.domain;

import nextstep.lms.domain.Course;
import nextstep.sessions.domain.SessionTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {
    public static final Course C = Course.of("TDD, 클린 코드 with Java", 1, 1L);

    @DisplayName("Course 객체가 잘 생성되는지 확인")
    @Test
    void Course_객체가_정상적으로_생성되는지_확인() {
        assertThat(Course.of("TDD, 클린 코드 with Java", 1, 1L)).isInstanceOf(Course.class);
    }

    @DisplayName("여러 개의 강의(Session)를 가질 수 있는지 확인")
    @Test
    void 여러개의_강의를_가질_수_있는지_확인() {
        //given
        Course course = Course.of("TDD, 클린 코드 with Java", 1, 1L);

        //when
        course.addSession(SessionTest.S1);
        course.addSession(SessionTest.S2);

        //then
        assertThat(course.getSessions().getSessions()).containsExactly(SessionTest.S1, SessionTest.S2);
    }
}
