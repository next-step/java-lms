package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

import java.util.List;

public class FreeEnrollments implements Enrollments {
    private final EnrollmentManager statusManager;

    public FreeEnrollments(List<NsUser> enrolledUsers, SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        this.statusManager = new EnrollmentManager(progressStatus, recruitmentStatus);

        // Initialize status manager with existing enrolled users
        for (NsUser user : enrolledUsers) {
            statusManager.addEnrollment(user);
            statusManager.approveEnrollment(user);
        }
    }

    public FreeEnrollments() {
        this.statusManager = new EnrollmentManager();
    }

    public void enroll(NsUser user) {
        statusManager.enroll(user);
    }

    @Override
    public void approve(NsUser user) {
        statusManager.approveEnrollment(user);
    }

    @Override
    public void cancel(NsUser user) {
        statusManager.cancelEnrollment(user);
    }

    public SessionProgressStatus getProgressStatus() {
        return statusManager.getProgressStatus();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return statusManager.getRecruitmentStatus();
    }

    @Override
    public List<NsUser> getEnrolledUsers() {
        return statusManager.getEnrolledUsers();
    }

    @Override
    public List<NsUser> getPendingApprovalUsers() {
        return statusManager.getPendingApprovalUsers();
    }

    @Override
    public EnrollmentStatus getEnrollmentStatus(NsUser user) {
        return statusManager.getEnrollmentStatus(user);
    }
}
