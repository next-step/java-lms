package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class FreeEnrollments implements Enrollments {
    private final EnrollmentManager enrollment;
    private final EnrollmentStatusManager statusManager;

    public FreeEnrollments(List<NsUser> enrolledUsers, SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        this.enrollment = new EnrollmentManager(enrolledUsers, progressStatus, recruitmentStatus);
        this.statusManager = new EnrollmentStatusManager();

        // Initialize status manager with existing enrolled users
        for (NsUser user : enrolledUsers) {
            statusManager.addEnrollment(user);
            statusManager.approveEnrollment(user);
        }
    }

    public FreeEnrollments() {
        this.enrollment = new EnrollmentManager(new ArrayList<>(), SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
        this.statusManager = new EnrollmentStatusManager();
    }

    public void enroll(NsUser user) {
        enrollment.enroll(user);
        statusManager.addEnrollment(user);
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
        return enrollment.getProgressStatus();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return enrollment.getRecruitmentStatus();
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
