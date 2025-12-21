package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.policy.FreeSessionPolicy;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    void 강의_추가에_성공한다() {
        Course course = new Course("강의제목", 1L);
        Session session = new Session(
                new SessionPeriod(LocalDate.now(), LocalDate.now().plusMonths(3)),
                new CoverImage("파일이름.png", 1024, 1500, 1000),
                new FreeSessionPolicy()
        );

        course.addSession(session);

        assertThat(course.sessionCount()).isEqualTo(1);
    }
}
