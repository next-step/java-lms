package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class PaidEnrollment implements Enrollment {
    private final int maxEnrollment;
    private final List<NsUser> enrolledUsers;

    public PaidEnrollment(int maxEnrollment) {
        validateMaxEnrollment(maxEnrollment);
        this.maxEnrollment = maxEnrollment;
        this.enrolledUsers = new ArrayList<>();
    }

    private void validateMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment <= 0) {
            throw new IllegalArgumentException("유료 강의는 최대 수강 인원이 0보다 커야 합니다.");
        }
    }

    @Override
    public void enroll(NsUser user) {
        if (isFull()) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaidEnrollment that = (PaidEnrollment) o;
        return maxEnrollment == that.maxEnrollment && enrolledUsers.equals(that.enrolledUsers);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(maxEnrollment, enrolledUsers);
    }
} 