package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Enrollments {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public Enrollments() {}

    public Enrollments(Enrollment... enrollment) {
        this.enrollments.addAll(List.of(enrollment));
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments.addAll(enrollments);
    }

    public void add(Enrollment enrollment) {
        validateNotDuplicate(enrollment);
        this.enrollments.add(enrollment);
    }

    public int size() {
        return this.enrollments.size();
    }

    public void validateNotDuplicate(Enrollment enrollment) {
        if (this.enrollments.contains(enrollment)) {
            throw new IllegalArgumentException("이미 신청한 강의입니다.");
        }
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    @Override
    public String toString() {
        return "Enrollments{" +
                "enrollments=" + enrollments +
                '}';
    }
}
