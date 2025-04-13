package nextstep.courses.domain.session.enrollment;

import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class FreeEnrollment implements Enrollment {
    private final List<NsUser> enrolledUsers;
    private final SessionStatus status;

    public FreeEnrollment() {
        this.enrolledUsers = new ArrayList<>();
        this.status = SessionStatus.RECRUITING;
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
        return false;
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
        return isRecruiting() && !hasEnrolledUser(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeEnrollment that = (FreeEnrollment) o;
        return enrolledUsers.equals(that.enrolledUsers) && status == that.status;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(enrolledUsers, status);
    }
} 