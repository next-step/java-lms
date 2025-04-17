package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Student {
    private final NsUser user;
    private final List<Enrollment> enrollments;

    public Student(NsUser user, List<Enrollment> enrollments) {
        this.user = user;
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }
}
