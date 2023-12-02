package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    @DisplayName("과정(객체)는 여러 개의 강의(객체)를 가질 수 있다.")
    void 과정_은_여러_개의_강의를_가질_수_있다() {
        List<Session> sessions = List.of(new Session());
        Course course = new Course(sessions);
        assertThat(course.getSessions()).isNotEmpty();
    }
}