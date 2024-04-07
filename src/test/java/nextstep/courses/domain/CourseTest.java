package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CourseTest {

    @DisplayName("한 과정(Course) 에 동일한 강의 (Session) 를 추가하면 예외가 발생한다.")
    @Test
    void test01() {
        Course course = new Course("TDD", 1L);
        Session session = new FreeSession(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        assertThatThrownBy(() -> {
            course.addSession(session);
            course.addSession(session);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 동일한 강의가 있습니다.");
    }
}
