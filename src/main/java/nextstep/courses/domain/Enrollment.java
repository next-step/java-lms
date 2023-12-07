package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Enrollment {
    private final int capacity;

    private final SessionStatus sessionStatus;

    private final List<Student> students;

    public Enrollment(int capacity, SessionStatus sessionStatus) {
        this(capacity, sessionStatus, new ArrayList<>());
    }

    public Enrollment(int capacity, SessionStatus sessionStatus, List<Student> students) {
        this.capacity = capacity;
        this.sessionStatus = sessionStatus;
        this.students = students;
    }

    public void enroll(Student student) throws AlreadyEnrollmentException {
        enroll(student, this.students);
    }

    public void enroll(Student student, List<Student> value) throws AlreadyEnrollmentException {
        if (!sessionStatus.isEnrolling()) {
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
        return capacity == that.capacity && sessionStatus == that.sessionStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, sessionStatus);
    }
}
