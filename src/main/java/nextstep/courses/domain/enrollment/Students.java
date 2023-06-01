package nextstep.courses.domain.enrollment;

import nextstep.courses.AlreadyEnrolledException;
import nextstep.courses.ExceedMaxEnrollmentException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Students {

    private final int capacity;
    private final List<Student> students;

    public Students(int capacity) {
        this(capacity, new ArrayList<>());
    }

    public Students(int capacity, List<Student> students) {
        if (capacity < students.size()) {
            throw new ExceedMaxEnrollmentException();
        }
        this.capacity = capacity;
        this.students = students;
    }

    public int countEnrollment() {
        return students.size();
    }

    public void enroll(Student student) {
        if (isAlreadyEnrolled(student)) {
            throw new AlreadyEnrolledException();
        }
        students.add(student);
        if (isExceededMaxEnrollment()) {
            students.remove(student);
            throw new ExceedMaxEnrollmentException();
        }
    }

    private boolean isAlreadyEnrolled(Student student) {
        return students.contains(student);
    }

    public List<Student> fetchStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public boolean isExceededMaxEnrollment() {
        return capacity < countEnrollment();
    }

    public int sessionCapacity() {
        return capacity;
    }
}
