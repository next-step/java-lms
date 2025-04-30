package nextstep.courses.domain.session.enrollment;

import nextstep.users.domain.NsUser;

public class Enrollment {
    private final NsUser user;
    private EnrollmentStatus enrollmentStatus;

    public Enrollment(NsUser user, EnrollmentStatus enrollmentStatus) {
        this.user = user;
        this.enrollmentStatus = enrollmentStatus;
    }

    public Enrollment(NsUser user) {
        this(user, EnrollmentStatus.PENDING_APPROVAL);
    }

    public NsUser getUser() {
        return user;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void approve() {
        if (enrollmentStatus == EnrollmentStatus.PENDING_APPROVAL) {
            enrollmentStatus = EnrollmentStatus.ENROLLED;
        } else {
            throw new IllegalStateException("승인 대기 상태의 수강신청만 승인할 수 있습니다.");
        }
    }

    public void cancel() {
        if (enrollmentStatus == EnrollmentStatus.PENDING_APPROVAL || 
            enrollmentStatus == EnrollmentStatus.ENROLLED) {
            enrollmentStatus = EnrollmentStatus.CANCELLED;
        } else {
            throw new IllegalStateException("승인 대기 또는 수강신청 상태만 취소할 수 있습니다.");
        }
    }

    public boolean isPendingApproval() {
        return enrollmentStatus == EnrollmentStatus.PENDING_APPROVAL;
    }

    public boolean isEnrolled() {
        return enrollmentStatus == EnrollmentStatus.ENROLLED;
    }

    public boolean isCancelled() {
        return enrollmentStatus == EnrollmentStatus.CANCELLED;
    }
}
