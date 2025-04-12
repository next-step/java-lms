package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Enrollment {
    private final int maxEnrollment;
    private final List<NsUser> enrolledUsers;

    public Enrollment(int maxEnrollment) {
        validateMaxEnrollment(maxEnrollment);
        this.maxEnrollment = maxEnrollment;
        this.enrolledUsers = new ArrayList<>();
    }

    private void validateMaxEnrollment(int maxEnrollment) {
        if (maxEnrollment < 0) {
            throw new IllegalArgumentException("최대 수강 인원은 0보다 커야 합니다.");
        }
    }

    public void enroll(NsUser user) {
        if (maxEnrollment > 0 && enrolledUsers.size() >= maxEnrollment) {
            throw new IllegalStateException("최대 수강 인원을 초과했습니다.");
        }
        enrolledUsers.add(user);
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public int getCurrentEnrollment() {
        return enrolledUsers.size();
    }

    public List<NsUser> getEnrolledUsers() {
        return Collections.unmodifiableList(enrolledUsers);
    }
} 