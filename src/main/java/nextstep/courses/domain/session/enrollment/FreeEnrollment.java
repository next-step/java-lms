package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class FreeEnrollment implements Enrollment {
    private final EnrollmentManager enrollment;

    public FreeEnrollment(List<NsUser> enrolledUsers, SessionStatus status) {
        this.enrollment = new EnrollmentManager(enrolledUsers, status);
    }

    public FreeEnrollment() {
        this.enrollment = new EnrollmentManager(new ArrayList<>(), SessionStatus.RECRUITING);
    }

    public void enroll(NsUser user) {
        enrollment.enroll(user);
    }

    public SessionStatus getStatus() {
        return enrollment.getStatus();
    }
}
