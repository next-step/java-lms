package nextstep.courses.domain.session.enrollment;

import lombok.Getter;
import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class PaidEnrollments implements Enrollments {
    private final EnrollmentManager statusManager;

    @Getter
    private final int maxEnrollment;

    public PaidEnrollments(int maxEnrollment, List<NsUser> enrolledUsers, SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
        validateMaxEnrollment(maxEnrollment);
        this.maxEnrollment = maxEnrollment;
        this.statusManager = new EnrollmentManager(progressStatus, recruitmentStatus);

        // Initialize status manager with existing enrolled users
        for (NsUser user : enrolledUsers) {
            statusManager.addEnrollment(user);
            statusManager.approveEnrollment(user);
        }
    }

    public PaidEnrollments(int maxEnrollment) {
        this(maxEnrollment, new ArrayList<>(), SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING);
    }

    public void enroll(NsUser user) {
        if (isFull()) {
            throw new IllegalStateException("수강 인원이 가득 찼습니다.");
        }
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

    private boolean isFull() {
        return statusManager.getEnrolledUsers().size() >= maxEnrollment;
    }

    private void validateMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
        }
    }
}
