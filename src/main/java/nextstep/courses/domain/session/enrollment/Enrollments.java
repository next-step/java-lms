package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

import java.util.List;

public interface Enrollments {
    void enroll(NsUser user);
    void approve(NsUser user);
    void cancel(NsUser user);
    SessionProgressStatus getProgressStatus();
    SessionRecruitmentStatus getRecruitmentStatus();
    List<NsUser> getEnrolledUsers();
    List<NsUser> getPendingApprovalUsers();
    EnrollmentStatus getEnrollmentStatus(NsUser user);
}
