package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment enrollment) {
        validateNotDuplicate(enrollment);
        this.enrollments.add(enrollment);
    }

    public int size() {
        return this.enrollments.size();
    }

    private void validateNotDuplicate(Enrollment enrollment) {
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
