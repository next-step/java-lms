package nextstep.courses.domain;

import nextstep.courses.exception.AlreadyAddStudentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {

    private final List<Student> students;

    public Students() {
        this(new ArrayList<>());
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public void enroll(Student student) {
        if (students.contains(student)) {
            throw new AlreadyAddStudentException("이미 수강을 신청한 학생입니다.");
        }

        students.add(student);
    }

    public boolean isFull(int capacity) {
        return capacity <= students.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students1 = (Students) o;
        return Objects.equals(students, students1.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(students);
    }
}
