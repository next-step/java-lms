package nextstep.courses.domain.enrollment;

import java.util.List;

public class Enrollment {

    private final int capacity;
    private Students students;

    public Enrollment(int capacity) {
        this.capacity = capacity;
        this.students = new Students(capacity);
    }

    public int sessionCapacity() {
        return students.sessionCapacity();
    }

    public int currentEnrolmentCount() {
        return students.countEnrollment();
    }

    public boolean hasEnrolledStudent() {
        return !students.isEmpty();
    }

    public void enroll(Student student, List<Student> addedStudents) {
        students = new Students(this.capacity, addedStudents);
        students.enroll(student);
    }
}
