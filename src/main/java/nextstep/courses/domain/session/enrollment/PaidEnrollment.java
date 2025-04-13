package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class PaidEnrollment implements Enrollment {
    private final int maxEnrollment;
    private final List<NsUser> enrolledUsers;
    private final SessionStatus status;

    public PaidEnrollment(int maxEnrollment) {
        validateMaxEnrollment(maxEnrollment);
        this.maxEnrollment = maxEnrollment;
        this.enrolledUsers = new ArrayList<>();
        this.status = SessionStatus.RECRUITING;
    }

    private void validateMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
        }
    }

    @Override
    public void enroll(NsUser user) {
        if (!canEnroll(user)) {
            throw new IllegalStateException("수강 신청이 불가능합니다.");
        }
        enrolledUsers.add(user);
    }

    @Override
    public boolean isFull() {
        return enrolledUsers.size() >= maxEnrollment;
    }

    @Override
    public boolean hasEnrolledUser(NsUser user) {
        return enrolledUsers.contains(user);
    }

    private boolean isRecruiting() {
        return status.isRecruiting();
    }

    private boolean canEnroll(NsUser user) {
        if (user == null) {
            throw new IllegalArgumentException("수강 신청할 사용자가 없습니다.");
        }
        return isRecruiting() && !isFull() && !hasEnrolledUser(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidEnrollment that = (PaidEnrollment) o;
        return maxEnrollment == that.maxEnrollment && enrolledUsers.equals(that.enrolledUsers) && status == that.status;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maxEnrollment, enrolledUsers, status);
    }
} 