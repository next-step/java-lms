package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.asm.Advice.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

    Image image;

    @BeforeEach
    void setUp() {
        image = new Image("test.png", 300, 200, 1_000);
    }

    @Test
    void 무료강의_생성() {
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();

        Session session1 = new Session.Builder(1L)
                .title("lms")
                .sessionType(SessionType.FREE)
                .image(image)
                .state(SessionState.RECRUITING)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createFreeEnrollment(new Students()))
                .build();

        Session session2 = new Session.Builder(2L)
                .title("lms2")
                .sessionType(SessionType.FREE)
                .image(image)
                .state(SessionState.RECRUITING)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createFreeEnrollment(new Students()))
                .build();

        course.add(session1);
        course.add(session2);

        assertThat(course.countOfSession()).isEqualTo(2);
    }

    @Test
    void 유료강의_생성() {
        Course course = new Course();
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session.Builder(1L)
                .title("lms")
                .sessionType(SessionType.PAID)
                .image(image)
                .state(SessionState.RECRUITING)
                .sessionDuration(now.plusDays(5), now.plusDays(30))
                .enrollment(Enrollment.createPaidEnrollment(new Students(), 10, 5_000))
                .build();
        course.add(session);

        assertThat(course.countOfSession()).isEqualTo(1);
    }
}
