package nextstep.courses.domain.session.enrollment;

import nextstep.users.domain.NsUser;

public class Enrollment {
    private final NsUser user;
    private final EnrollmentStatus enrollmentStatus;

    public Enrollment(NsUser user, EnrollmentStatus enrollmentStatus) {
        this.user = user;
        this.enrollmentStatus = enrollmentStatus;
    }
}
