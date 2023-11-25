package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {

    @Test
    @DisplayName("과정에 세션을 추가할 수 있다")
    public void add_session() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        Session session = new Session(LocalDate.now(), LocalDate.now(), new Image(1, "JPG", 300, 200), SessionStatus.READY);

        course.addSession(session);
        assertThat(course.sessions()).isEqualTo(Arrays.asList(session));
    }

}
