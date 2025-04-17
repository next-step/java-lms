package nextstep.courses.domain;

import java.util.List;

public class Enrollments {
    private final List<Enrollment> enrollments;

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public int count() {
        return enrollments.size();
    }
}
