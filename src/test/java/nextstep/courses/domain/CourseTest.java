package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @Test
    @DisplayName("과정에 강의를 추가할 수 있다.")
    void add_session() {
        Course course = new Course(0L, "TDD, 클린 코드 with Java 17기", 1L);
        CoverImage coverImage = new CoverImage(1, "jpg", 300, 200);
        LocalDate now = LocalDate.now();
        Session session = Session.ofFree(0L, 1L, coverImage, now, now);

        course.addSession(session);

        assertThat(course.sessions().get(0)).isEqualTo(session);
    }
}
