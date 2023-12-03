package nextstep.courses.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {

    private List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}
