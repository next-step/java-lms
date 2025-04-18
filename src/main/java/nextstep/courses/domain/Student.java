package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Student {
    private final NsUser user;
    private final Enrollments enrollments;

    public Student(NsUser user, Enrollments enrollments) {
        this.user = user;
        this.enrollments = enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }
}
