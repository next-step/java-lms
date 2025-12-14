package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {
    private List<Enrollment> enrollments;

    public Enrollments() {
        this(new ArrayList<>());
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void enroll(Enrollment enrollment) {
        validationDuplicate(enrollment);
        this.enrollments.add(enrollment);
    }

    public int countEnrollments() {
        return this.enrollments.size();
    }

    public void validationDuplicate(Enrollment enrollment) {
        if (enrollments.contains(enrollment)) {
            throw new IllegalArgumentException("이미 수강 신청한 강의입니다.");
        }
    }
}
