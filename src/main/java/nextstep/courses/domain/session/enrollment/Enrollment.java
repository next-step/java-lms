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
        enrollmentStatus = enrollmentStatus.approve();
    }

    public void cancel() {
        enrollmentStatus = enrollmentStatus.cancel();
    }

    public boolean isPendingApproval() {
        return enrollmentStatus.isPendingApproval();
    }

    public boolean isEnrolled() {
        return enrollmentStatus.isEnrolled();
    }

    public boolean isCancelled() {
        return enrollmentStatus.isCancelled();
    }
}
