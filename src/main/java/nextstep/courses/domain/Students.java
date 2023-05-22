package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> students;

    public Students() {
        this(new ArrayList<>());
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public void enroll(Student student) throws AlreadyEnrollmentException {
        if (students.contains(student)) {
            throw new AlreadyEnrollmentException(student + "는 이미 수강 신청한 학생입니다.");
        }
        students.add(student);
    }

    public boolean enrolledUser(Student student) {
        return students.contains(student);
    }

    public int sizeOfStudents() {
        return students.size();
    }

    public boolean isFullCapacity(int sessionCapacity) {
        return this.sizeOfStudents() == sessionCapacity;
    }
}
