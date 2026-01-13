package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.session.EnrollmentPolicy;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CourseTest {
    private CoverImage coverImage;
    private Period period;

    @BeforeEach
    void setUp() {
        coverImage = new CoverImage(1_048_576L, ImageType.JPG, 300, 200);
        period = new Period(LocalDate.of(2025, 11, 3), LocalDate.of(2025, 12, 18));
    }

    @Test
    void 과정은_여러_강의를_가질_수_있다() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);

        Session session1 = new Session(period, coverImage, SessionStatus.RECRUITING,
            new EnrollmentPolicy(SessionType.FREE, 15, 0L));
        Session session2 = new Session(period, coverImage, SessionStatus.PREPARING,
            new EnrollmentPolicy(SessionType.PAID, 30, 50000L));

        course.addSession(session1);
        course.addSession(session2);

        assertThat(course.sessionCount()).isEqualTo(2);
    }

}
