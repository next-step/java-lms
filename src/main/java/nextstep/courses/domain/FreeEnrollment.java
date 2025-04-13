package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class FreeEnrollment implements Enrollment {
    private final List<NsUser> enrolledUsers;

    public FreeEnrollment() {
        this.enrolledUsers = new ArrayList<>();
    }

    @Override
    public void enroll(NsUser user) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeEnrollment that = (FreeEnrollment) o;
        return enrolledUsers.equals(that.enrolledUsers);
    }

    @Override
    public int hashCode() {
        return enrolledUsers.hashCode();
    }
} 