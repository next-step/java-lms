package nextstep.courses.domain.session.enrollment;

import lombok.Getter;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class PaidEnrollment implements Enrollment{
    private final EnrollmentManager enrollment;

    @Getter
    private final int maxEnrollment;

    public PaidEnrollment(int maxEnrollment, List<NsUser> enrolledUsers, SessionStatus status) {
        validateMaxEnrollment(maxEnrollment);
        this.maxEnrollment = maxEnrollment;
        this.enrollment = new EnrollmentManager(enrolledUsers, status);
    }

    public PaidEnrollment(int maxEnrollment) {
        this(maxEnrollment, new ArrayList<>(), SessionStatus.RECRUITING);
    }

    public void enroll(NsUser user) {
        if (isFull()) {
            throw new IllegalStateException("수강 인원이 가득 찼습니다.");
        }
        enrollment.enroll(user);
    }

    public SessionStatus getStatus() {
        return enrollment.getStatus();
    }

    private boolean isFull() {
        return enrollment.getEnrolledUsers().size() >= maxEnrollment;
    }

    private void validateMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
        }
    }
}
