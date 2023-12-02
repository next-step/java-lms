package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {

    public static Course course = new Course("TDD with java", 1L);

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("과정은 강의가 추가될 수 있다.")
    void course_AddSession_Test() {
        assertThat(course.getSessions().size()).isEqualTo(0);
        Session session1 = new Session("과제3 - 사다리게임");
        Session session2 = new Session("과제4 - 레거시 리팩토링");
        course.addSession(session1);
        course.addSession(session2);
        assertThat(course.getSessions().size()).isEqualTo(2);
    }
}
