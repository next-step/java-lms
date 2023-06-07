package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.enums.SessionStatus;

public class EnrollmentFixture {

    public static final Enrollment 강의_진행중 = new Enrollment(SessionStatus.ENROLLING, 3);

    public static final Enrollment 강의_종료 = new Enrollment(SessionStatus.FINISH, 0);

    public static final Enrollment 강의_준비중 = new Enrollment(SessionStatus.PREPARING, 0);
}
