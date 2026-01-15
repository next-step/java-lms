package nextstep.courses.domain.enroll;

import java.util.List;

public class Enrollments {
    private final List<Enrollment> enrollments;

    public Enrollments(Enrollment... enrollments) {
        this.enrollments = List.of(enrollments);
    }

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }
}
