package nextstep.courses.domain.fixture;

import nextstep.courses.domain.*;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;

public class SessionFixtures {
    public static final NsUser USER = new NsUser(1L, "user", "password", "name", "user1@test.test");
    public static final Student STUDENT_1 = new Student(1L, 1L);
    public static final Student STUDENT_2 = new Student(1L, 2L);
    public static final CoverImage COVER_IMAGE = new CoverImage(1L, "https://test.test/images/0");
    public static final Enrollment ENROLLMENT = new Enrollment(
            SessionStatus.PREPARING,
            SessionType.FREE,
            10
    );
    public static final Session SESSION = new Session(
            2L,
            "hello",
            COVER_IMAGE,
            new SessionPeriod(
                    LocalDate.of(2023, 5, 24),
                    LocalDate.of(2023, 5, 25)
            ),
            ENROLLMENT
    );

}
