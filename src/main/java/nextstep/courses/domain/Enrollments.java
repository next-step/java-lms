package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {
    List<Enrollment> enrollments;

    public Enrollments() {
        enrollments = new ArrayList<>();
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void enroll(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }
}
