package nextstep.courses.domain.enrollment;

import java.util.ArrayList;
import java.util.List;

public class Enrollments {

    private final List<Enrollment> enrollments;

    public Enrollments() {
        this(new ArrayList<>());
    }

    public Enrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public void add(Enrollment enrollment) {
        validateAlreadyRegistered(enrollment);
        this.enrollments.add(enrollment);
    }

    private void validateAlreadyRegistered(Enrollment enrollment) {
        if (enrollments.contains(enrollment)) {
            throw new RuntimeException("이미 수강 신청한 강의입니다.");
        }
    }

    public int size() {
        return enrollments.size();
    }

    public List<Long> studentIds() {
        List<Long> studentIds = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            studentIds.add(enrollment.studentId());
        }
        return studentIds;
    }
}
