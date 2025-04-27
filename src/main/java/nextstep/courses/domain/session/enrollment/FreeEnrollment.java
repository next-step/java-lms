package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class FreeEnrollment implements Enrollment {
    private final EnrollmentManager enrollment;

    public FreeEnrollment(List<NsUser> enrolledUsers, SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        this.enrollment = new EnrollmentManager(enrolledUsers, progressStatus, recruitmentStatus);
    }

    public FreeEnrollment() {
        this.enrollment = new EnrollmentManager(new ArrayList<>(), SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
    }

    public void enroll(NsUser user) {
        enrollment.enroll(user);
    }

    public SessionProgressStatus getProgressStatus() {
        return enrollment.getProgressStatus();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return enrollment.getRecruitmentStatus();
    }
}
