package nextstep.courses.domain;

import java.util.List;
import java.util.Objects;

public class Enrollment {
    private final int capacity;

    private final EnrollmentStatus enrollmentStatus;

    public Enrollment(int capacity, EnrollmentStatus enrollmentStatus) {
        this.capacity = capacity;
        this.enrollmentStatus = enrollmentStatus;
    }

    public void enroll(Student student, List<Student> value) throws AlreadyEnrollmentException {
        if (!enrollmentStatus.isEnrolling()) {
            throw new IllegalArgumentException("수강신청 상태가 아니라 수강신청할 수 없습니다.");
        }
        Students students = new Students(this.capacity, value);
        students.enroll(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return capacity == that.capacity && enrollmentStatus == that.enrollmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, enrollmentStatus);
    }
}
